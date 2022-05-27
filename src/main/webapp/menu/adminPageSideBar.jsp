<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
	
<div class="sidebar">
		<div class="logo-details">
			<!-- <i class='bx bxl-c-plus-plus'></i> -->
			<span class="logo_name">BY PLACE</span>
		</div>
		<ul class="nav-links">
			<li>
				<div class="iocn-link">
					<a href="#"> <span class="link_name">카테고리관리</span>
					</a> <i class='bx bxs-chevron-down arrow'></i>
				</div>
				<ul class="sub-menu">
					<li><a class="link_name" href="#">카테고리관리</a></li>
					<li><a href="./adminPage_categoryList">카테고리보기</a></li>
				</ul>
			</li>
			<li>
				<div class="iocn-link">
					<a href="#"> <span class="link_name">사용자관리</span>
					</a> <i class='bx bxs-chevron-down arrow'></i>
				</div>
				<ul class="sub-menu">
					<li><a class="link_name" href="#">사용자관리</a></li>
					<li><a href="./adminPage_userList">유저리스트</a></li>
					<li><a href="./adminPage_userAuthList">권한승인</a></li>
					<li><a href="./adminPage_userWithdrawalList">탈퇴리스트</a></li>
					<li><a href="./adminPage_userBlackList">블랙리스트</a></li>
				</ul>
			</li>
			<li>
				<div class="iocn-link">
					<a href="#"> <span class="link_name">음식점관리</span>
					</a> <i class='bx bxs-chevron-down arrow'></i>
				</div>
				<ul class="sub-menu">
					<li><a class="link_name" href="#">음식점관리</a></li>
					<li><a href="./adminPage_restaurantList">음식점보기</a></li>
					<li><a href="./adminPage_restaurantApprovalList">음식점승인</a></li>
					<li><a href="#">음식점복구</a></li>
				</ul>
			</li>
			<li>
				<div class="iocn-link">
					<a href="#"> <span class="link_name">대표메뉴관리</span>
					</a> <i class='bx bxs-chevron-down arrow'></i>
				</div>
				<ul class="sub-menu">
					<li><a class="link_name" href="#">대표메뉴관리</a></li>
					<li><a href="#">대표메뉴보기</a></li>
				</ul>
			</li>
			<li>
				<div class="iocn-link">
					<a href="#"> <span class="link_name">리뷰관리</span>
					</a> <i class='bx bxs-chevron-down arrow'></i>
				</div>
				<ul class="sub-menu">
					<li><a class="link_name" href="#">리뷰관리</a></li>
					<li><a href="#">리뷰보기</a></li>
				</ul>
			</li>
			<li>
				<div class="iocn-link">
					<a href="#"> <span class="link_name">자유게시판관리</span>
					</a> <i class='bx bxs-chevron-down arrow'></i>
				</div>
				<ul class="sub-menu">
					<li><a class="link_name" href="#">자유게시판관리</a></li>
					<li><a href="#">자유게시판보기</a></li>
				</ul>
			</li>
			<li>
				<div class="iocn-link">
					<a href="#"> <span class="link_name">공지사항관리</span>
					</a> <i class='bx bxs-chevron-down arrow'></i>
				</div>
				<ul class="sub-menu">
					<li><a class="link_name" href="#">공지사항관리</a></li>
					<li><a href="#">공지사항보기</a></li>
				</ul>
			</li>
		</ul>
	</div>