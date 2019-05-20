<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery.min.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!--添加员工 Modal模态框 -->
	<div class="modal fade" id="emp_add_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增员工</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="empName"
									id="emp_add_input" placeholder="empName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="email"
									id="email_add_input" placeholder="email@163.com">
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">gender</label>
								<div class="col-sm-4">
									<label class="radio-inline"> <input type="radio"
										name="gender" id="gender1_add_input" value="M" checked="checked"> 男
									</label> <label class="radio-inline"> <input type="radio"
										name="gender" id="gender2_add_input" value="F"> 女
									</label>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<select class="form-control" name="did">
								  
								</select>
							</div>	
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_add_save">保存</button>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_btn">新增</button>
				<button class="btn btn-danger" id="emp_del_btn">删除</button>
			</div>
		</div>
		<div class="row">
			<table class="table table-hover" id="emp_table">
				<thead>
					<tr>
						<th>#</th>
						<th>emp_name</th>
						<th>gender</th>
						<th>email</th>
						<th>departName</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-md-6" id="page_info_area"></div>
			<div class="col-md-6" id="page_info_nav"></div>
		</div>
	</div>
	<script type="text/javascript">
			var totalRel;
		$(function() {
			to_page(1);

		});

		function to_page(pn) {
			
			$.ajax({
				url : "${APP_PATH}/emps",
				data : "pn=" + pn,
				type : "get",
				success : function(data) {
					build_emps_table(data);
					build_page_info(data);
					build_page_nav(data);
				}
			});
		}

		function build_emps_table(data) {
			
			$("#emp_table tbody").empty();
			var emps = data.map.pageInfo.list;
			$.each(emps, function(index, item) {
				console.log(item);
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(item.gender == "M" ? "男" : "女");
				var emailTd = $("<td></td>").append(item.email);
				var depatNameTd = $("<td></td>").append(item.department.deptName);
				var editBtn = $("<button></button>").append("编辑").addClass("btn btn-primary btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-pencil"));
				var deletBtn = $("<button></button>").append("删除").addClass("btn btn-danger btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-trash"));
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(deletBtn);
				$("<tr></tr>").append(empIdTd).append(empNameTd).append(genderTd).append(emailTd).append(depatNameTd).append(btnTd).appendTo("#emp_table tbody");
			});

		}

		function build_page_info(data) {
			console.log(data);
			$("#page_info_area").empty();
			$("#page_info_area").append("当前第" + data.map.pageInfo.pageNum + " 页,总共"+ data.map.pageInfo.pages + " 页,共 "+ data.map.pageInfo.total + "条记录;")
							totalRel=data.map.pageInfo.total;
		}

		function build_page_nav(data) {
			$("#page_info_nav").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			var fistPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			if (data.map.pageInfo.hasPreviousPage == false) {
				fistPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {
				fistPageLi.click(function() {
					to_page(1);
				})
				prePageLi.click(function() {
					to_page(data.map.pageInfo.pageNum - 1);
				})
			}
			if (data.map.pageInfo.hasNextPage == false) {
				lastPageLi.addClass("disabled");
				nextPageLi.addClass("disabled");
			} else {
				lastPageLi.click(function() {
					to_page(data.map.pageInfo.pages);
				});
				nextPageLi.click(function() {
					to_page(data.map.pageInfo.pageNum + 1);
				})
			}

			ul.append(fistPageLi).append(prePageLi);
			$.each(data.map.pageInfo.navigatepageNums, function(index, item) {
				var numsLi = $("<li></li>").append($("<a></a>").append(item));
				if (data.map.pageInfo.pageNum == item) {
					numsLi.addClass("active");
				}
				numsLi.click(function() {
					to_page(item);
				})
				ul.append(numsLi);
			})
			ul.append(nextPageLi).append(lastPageLi);
			var nav = $("<nav></nav>").append(ul);
			nav.appendTo("#page_info_nav");
		}

		$("#emp_add_btn").click(function() {
			$("#emp_add_modal").modal({
				backdrop : "static"
			})
		})
		
		$("#emp_add_btn").click(function(){
			
			$("#emp_add_modal select").empty();
			getDepts();
		})
		
		function getDepts()
		{
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"get",
				success:function(data){
					$.each(data.map.depts,function(index,item){
						var optionEl=$("<option></option").append(item.deptName).attr("value",item.deptId);
						optionEl.appendTo("#emp_add_modal select");
					})
				}
			})
		}
		
		$("#emp_add_save").click(function(){
			
			$.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				data:$("#emp_add_modal form").serialize(),
				success:function(result){
					
					$("#emp_add_modal").modal('hide');
					to_page(totalRel);
				}
			})
		});
	</script>
</body>
</html>