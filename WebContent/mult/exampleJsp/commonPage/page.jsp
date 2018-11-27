<%@ page import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 
	配置Page javaBean使用，分页实现：下拉选，指定选，队列选及每页条数
 -->

	第${page.currentPage }页&nbsp;共${page.lastPage }页
   	<a href="javascript:toPage(${page.pageNum},${page.firstPage})">首页</a>
   	<a href="javascript:toPage(${page.pageNum},${page.previouPage})">上一页</a>
   	
   	<c:forEach begin="${page.startPage }" end="${page.endPage }" var="num">
   		<a href="javascript:toPage(${page.pageNum},${num})">${num }</a>
   	</c:forEach>
   	
   	<a href="javascript:toPage(${page.pageNum},${page.nextPage})">下一页</a>
   	<a href="javascript:toPage(${page.pageNum},${page.lastPage})">尾页</a>
   	第
   	<select id="jumpNum">
   		<c:forEach begin="${page.firstPage }" end="${page.lastPage }" var="num">
   			<c:choose>
   				<c:when test="${page.currentPage eq num }">
	    			<option value="${num }" selected="selected" >${num }</option>
   				</c:when>
   				<c:otherwise>
   					<option value="${num }">${num }</option>
   				</c:otherwise>
   			</c:choose>
   		</c:forEach>
   	</select>
   	页
   	<input type="button" onclick="jumb();" value="跳转">
   	&nbsp;
   	每页条数：
   	<input id="pageNum" size="1" value="${page.pageNum }">
   	
   	<script type="text/javascript">
   		function jumb(){
   			//获取每页条数
   			var pageNum = document.getElementById("pageNum");
   			//获取跳转第几页
   			var jumpNum = document.getElementById("jumpNum");
   			toPage(pageNum.value,jumpNum.value);
   		}
   		
   		function toPage(pageNum,currentPage){
   			window.location.href="${pageContext.request.contextPath }${page.url}&currentPage="+currentPage+"&pageNum="+pageNum;
   		}
   	</script>
