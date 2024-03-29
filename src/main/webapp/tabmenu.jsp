<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main">
<%@ include file="/favicon.jsp"%>
		<div class="container">
<!-- 탭 메뉴 상단 시작 -->
	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">전체</li>
		<li class="tab-link" data-tab="tab-2">한식</li>
		<li class="tab-link" data-tab="tab-3">양식</li>
		<li class="tab-link" data-tab="tab-4">중식</li>
		<li class="tab-link" data-tab="tab-5">일식</li>
		<li class="tab-link" data-tab="tab-6">분식</li>
		<li class="tab-link" data-tab="tab-7">카페,디저트</li>
		<li class="tab-link" data-tab="tab-8">치킨</li>
		<li class="tab-link" data-tab="tab-9">피자</li>
		<li class="tab-link" data-tab="tab-10">고기,구이</li>
		<li class="tab-link" data-tab="tab-11">족발,보쌈</li>
		<li class="tab-link" data-tab="tab-12">패스트푸드</li>
		<li class="tab-link" data-tab="tab-13">야식</li>
	</ul>
<!-- 탭 메뉴 상단 끝 -->
<!-- 탭 메뉴 내용 시작 -->
	<div id="tab-1" class="tab-content current">
    <c:if test="${tabmenu.category_category eq '한식' }">
    
    </c:if>

	</div>
	<div id="tab-2" class="tab-content">
	<h1>한식</h1>
	</div>
	<div id="tab-3" class="tab-content">
  	<h1>양식</h1>
	</div>
	<div id="tab-4" class="tab-content">
  	<h1>중식</h1>
	</div>
	<div id="tab-5" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-6" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-7" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-8" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-9" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-10" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-11" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-12" class="tab-content">
  	<h1>일식</h1>
	</div>
	<div id="tab-13" class="tab-content">
  	<h1>일식</h1>
	</div>
<!-- 탭 메뉴 내용 끝 -->
	</div>	
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})

});
</script>