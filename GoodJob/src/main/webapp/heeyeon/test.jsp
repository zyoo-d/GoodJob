<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
.optionJob {
	border: 1px solid #dee2e6;
	border-radius: 4px;
	overflow: hidden;
}

.row_item {
	display: flex;
	border-bottom: 1px solid #ccc;
}

.row_item:last-child {
	border-bottom: none;
}

.category-list {
	width: 150px;
	padding: 10px;
	background-color: #f1f1f1;
	border-right: 1px solid #ccc;
	text-align: center;
}

.category-list h3 {
	margin: 0;
	cursor: pointer;
}

.tag-group {
	flex-grow: 1;
	padding: 10px;
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
}

.tag-group label {
	font-size: 0.8rem;
	display: inline-block;
	padding: 3px 8px;
	background-color: #f1f1f1;
	border-radius: 4px;
	cursor: pointer;
}

.tag-group input[type="checkbox"] {
	display: none;
}

.tag-group input[type="checkbox"]:checked+label {
	background-color: #007bff;
	color: #fff;
}

.toggle-button {
	display: inline-block;
	margin-left: 5px;
	cursor: pointer;
}
</style>
<script src="https://kit.fontawesome.com/d2e20c7551.js"
	crossorigin="anonymous"></script>
</head>
<%@include file="/WEB-INF/views/inc/header.jsp"%>
<body>
	<section class="integration-single section pt-0">
		<div class="container">
			<div class="row justify-center">
				<div class="lg:col-10 my-16">
					<div
						class="integration-single-container rounded-xl bg-white py-16 px-5 shadow-lg md:px-10 tab"
						data-tab-group="service-tab">
						<div class="px-4 text-center">
							<h5 class="text-left">선호직무</h5>
							<div class="optionJob mt-4">
								<dl class="row_item">
									<dt class="category-list">
										<h6>
											직무·직업<span class="toggle-button"
												onclick="toggleTagList(event)">+</span>
										</h6>
									</dt>
									<dd class="tag-group">
										<input type="checkbox" id="tag1" value="데이터엔지니어"> <label
											for="tag1">데이터엔지니어</label> <input type="checkbox" id="tag2"
											value="프론트엔드"> <label for="tag2">프론트엔드</label>
									</dd>
								</dl>

								<dl class="row_item">
									<dt class="category-list">
										<h6>
											전문분야<span class="toggle-button"
												onclick="toggleTagList(event)">+</span>
										</h6>
									</dt>
									<dd class="tag-group">
										<input type="checkbox" id="tag3" value="자바"> <label
											for="tag3">자바</label> <input type="checkbox" id="tag4"
											value="AWS"> <label for="tag4">AWS</label> <input
											type="checkbox" id="tag5" value="Python"> <label
											for="tag5">Python</label>
									</dd>
								</dl>

								<dl class="row_item">
									<dt class="category-list">
										<h6>
											기술스택<span class="toggle-button"
												onclick="toggleTagList(event)">+</span>
										</h6>
									</dt>
									<dd class="tag-group">
										<input type="checkbox" id="tag6" value="React"> <label
											for="tag6">React</label> <input type="checkbox" id="tag7"
											value="웹개발"> <label for="tag7">웹개발</label> <input
											type="checkbox" id="tag8" value="ABAP"> <label
											for="tag8">ABAP</label>
									</dd>
								</dl>
							</div>
						</div>


					</div>
				</div>
			</div>
		</div>
	</section>

	<%@include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
		
	</script>
</body>
</html>