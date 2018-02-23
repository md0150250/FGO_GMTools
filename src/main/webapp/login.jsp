<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<CENTER>

		<!--  <img src="../host-manager/img/dog.jpg"> -->
		<Form Action="<c:url value='/loginServlet' />" method="POST">

			<Table width='330'>
				<TR>
					<TH colspan='2'>
						<H1>登入</H1>
					</TH>
					<TH></TH>
				</TR>
				<TR>
					<TD align="RIGHT">Email：</TD>
					<TD align="LEFT"><input type="text" name="gmEmail" size="10"
						value="${message.account}"></TD>
					<TD width='120'><small><Font color='red' size="-1">${message.aerror}</Font></small></TD>
				</TR>
				<TR>
					<TD align="RIGHT">密碼：</TD>
					<TD align="LEFT"><input type="password" name="password"
						size="10" value="${message.password}"></TD>
					<TD width='120'><small><Font color='red' size="-1">${message.perror}</Font></small></TD>
				</TR>
				<TR>
					<TD align="CENTER" colspan='3'><Font color='red' size="-1">${ErrorMsgKey.LoginError}&nbsp;</Font></TD>

				</TR>


				<TR>
					<TD colspan="2" align="center"><input type="submit" value="登入">
					</TD>
				</TR>
			</Table>

		</Form>
	</CENTER>
</body>
</html>