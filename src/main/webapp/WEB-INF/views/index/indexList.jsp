<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="xqlc" uri="http://www.xqlc.com/xqlc"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="locale" value="${pageContext.request.locale}" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- BEGIN PAGE HEADER-->
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a href="index.html">Home</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href="#"></a></li>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<div class="tabbable-line boxless tabbable-reversed">

			<div class="tab-pane" id="tab_2">
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">
							<i class="glyphicon glyphicon-search"></i>条件查询
						</div>
					</div>
					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form id="queryForm" action="${ctx}/layout_ajax_content_1" class="form-horizontal" method="get">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-3">Select 1</label>
											<div class="col-md-9">
												<input type="text" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-3">Select 2</label>
											<div class="col-md-9">
												<input type="text" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-3">Select 3</label>
											<div class="col-md-9">
												<input type="text" class="form-control">
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-3">Select 4</label>
											<div class="col-md-9">
												<input type="text" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-3">Select 5</label>
											<div class="col-md-9">
												<input type="text" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-3">Select 6</label>
											<div class="col-md-9">
												<input type="text" class="form-control">
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
									<input id="queryForm_page_id" name="page.page" value="" type="hidden">
									</div>
									<div class="col-md-4">
										<button type="submit" class="btn btn-sm green submit">查询</button>
										<button type="button" class="btn btn-sm default">重置</button>
									</div>
								</div>
							</div>
						</form>
						<!-- END FORM-->
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<div class="row">
	<!-- BEGIN EXAMPLE TABLE PORTLET-->
	<div class="col-md-12 col-sm-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box yellow">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-user"></i>Table
				</div>
				<div class="actions">
					<a href="javascript:;" class="btn btn-default btn-sm"> <i
						class="fa fa-pencil"></i> Add
					</a>
					<div class="btn-group">
						<a class="btn btn-default btn-sm" href="javascript:;"
							data-toggle="dropdown"> <i class="fa fa-cogs"></i> Tools <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right">
							<li><a href="javascript:;"> <i class="fa fa-pencil"></i>
									Edit
							</a></li>
							<li><a href="javascript:;"> <i class="fa fa-trash-o"></i>
									Delete
							</a></li>
							<li><a href="javascript:;"> <i class="fa fa-ban"></i>
									Ban
							</a></li>
							<li class="divider"></li>
							<li><a href="javascript:;"> <i class="i"></i> Make admin
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover"
					id="sample_2">
					<thead>
						<tr>
							<th class="table-checkbox"><input type="checkbox"
								class="group-checkable" data-set="#sample_2 .checkboxes" /></th>
							<th>用户名</th>
							<th>登录名</th>
							<th>密码</th>
							<th>创建时间</th>
							<th>创建人</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${pageInfo.content}" var="userInfo">
						<tr class="odd gradeX">
							<td><input type="checkbox" class="checkboxes" value="${userInfo.id}" /></td>
							<td>${userInfo.userName}</td>
							<td>${userInfo.loginName}</td>
							<td>${userInfo.passWord}</td>
							<td>${userInfo.createTime}</td>
							<td>${userInfo.createUser}</td>
						</tr>
					</c:forEach>
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>shuxer</td>
						<td><a class="ajaxify" href="${ctx}/layout_ajax_content_1"> shuxer@gmail.com
						</a></td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
							<td><a class="ajaxify" href="${ctx}/layout_ajax_content_1"> shuxer@gmail.com
						</a></td>
						<td><span class="label label-sm label-success">
								Approved </span></td>	
					</tbody>
				</table>
				<div class="pagination" style="text-align: right;width:100%;margin-top: 0px; margin-bottom: 0px;">
					<xqlc:page page="${pageInfo }" formId="queryForm"></xqlc:page>
<!-- 					<nav> -->
<!-- 					  <ul class="pagination"> -->
<!-- 					    <li class="disabled"><a href="#" title="首页">&laquo;</a></li> -->
<!-- 					    <li class="disabled"><a href="#" title="上页">&lsaquo;</a></li> -->
<!-- 					    <li><a href="#">...</a></li> -->
<!-- 					    <li class="active"><a href="#">1</a></li> -->
<!-- 					    <li><a href="#">2</a></li> -->
<!-- 					    <li><a href="#">3</a></li> -->
<!-- 					    <li><a href="#">4</a></li> -->
<!-- 					    <li><a href="#">25</a></li> -->
<!-- 					    <li><a href="#">...</a></li> -->
<!-- 					    <li><a href="#" title="下页">&rsaquo;</a></li> -->
<!-- 					    <li><a href="#" title="末页">&raquo;</a></li> -->
<!-- 					  </ul> -->
<!-- 					</nav> -->
				</div>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT-->
<script type="text/javascript">
// 	function toPage(formId, page) {
// 		var $form = $('#' + formId);
// 		reg = /^[0-9]*$/;
// 		if (reg.exec(page + "")) {
// 			$("#" + formId + "_page_id").val(page);
// 		}
// 		console.log("------------------------submit:");
// 		//$form[0].submit();
// 		$('.page-content .submit').trigger("click",[formId]);
// 	}

 </script>