<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="get" action="${pageContext.request.contextPath }/user/findByPage">
					 <span>用户名：</span>
					 <input name="queryname" class="input-text"	type="text" value="${queryUserName }">
					 
					 <span>用户角色：</span>
					 <select name="queryUserRole">
						<c:if test="${roleList != null }">
						   <option value="0">--请选择--</option>
						   <c:forEach var="role" items="${roleList}">
						   		<option <c:if test="${role.id == queryUserRole }">selected="selected"</c:if>
						   		value="${role.id}">${role.roleName}</option>
						   </c:forEach>
						</c:if>
	        		</select>
					 
					
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/user/useradd.html" >添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户角色</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="user" items="${userPage.pageList}" varStatus="status">
					<tr>
						<td>
						<span>${user.userCode }</span>
						</td>
						<td>
						<span>${user.userName }</span>
						</td>
						<td>
							<span>
								<c:if test="${user.gender==1}">男</c:if>
								<c:if test="${user.gender==2}">女</c:if>
							</span>
						</td>
						<td>
						<span>${user.age}</span>
						</td>
						<td>
						<span>${user.phone}</span>
						</td>
						<td>
							<span>${user.userRoleName}</span>
						</td>
						<td>
						<span><a class="viewUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/static/images/read.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyUser" href="javascript:void(0);" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/static/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a class="deleteUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/static/images/schu.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="page-bar">
			<ul class="page-num-ul clearfix">
				<li>共${userPage.totalCount }条记录&nbsp;&nbsp; ${userPage.pageNo }/${userPage.totalpages }页</li>
				<li>	
					<a href="${pageContext.request.contextPath}/user/findByPage?pageNo=1&queryname=${queryUserName}&queryUserRole=${queryUserRole}">首页</a>
					<a href="${pageContext.request.contextPath}/user/findByPage?pageNo=${userPage.pageNo-1}&queryname=${queryUserName}&queryUserRole=${queryUserRole}">上一页</a>
					<a href="${pageContext.request.contextPath}/user/findByPage?pageNo=${userPage.pageNo+1}&queryname=${queryUserName}&queryUserRole=${queryUserRole}">下一页</a>
					<a href="${pageContext.request.contextPath}/user/findByPage?pageNo=${userPage.totalpages}&queryname=${queryUserName}&queryUserRole=${queryUserRole}">最后一页</a>
				&nbsp;&nbsp;
				</li>	
			</ul>
	<!-- 	 <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
		</span> -->
		</div> 
		<br>
        <div class="providerAdd" id="myview" style="display: none">
			 <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" readonly="readonly"> 
					<font color="red"></font>
             </div>
			 <div>
                    <label >用户性别：</label>
                    <select name="gender" id="gender" >				
					 </select>
             </div>
			 <div>
                    <label for="data">出生日期：</label>
                    <input type="text" readonly="readonly" id="birthday"/>
                    <font color="red"></font>
              </div>
			
		       <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="phone" id="phone" readonly="readonly"> 
                    <font color="red"></font>
               </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="address" readonly="readonly">
                </div>
				<div>
                    <label >用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<select name="userRole" id="userRole"></select>
        			<font color="red"></font>
                </div>
        </div>
		
			
			<%--  <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
		 	<c:import url="rollpage.jsp">
	          	<c:param name="totalCount" value="${totalCount}"/>
	          	<c:param name="currentPageNo" value="${currentPageNo}"/>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import> --%>
        </div>
        </section>

<%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/userlist.js"></script>
