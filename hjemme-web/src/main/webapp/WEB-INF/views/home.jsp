<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

<head>
<title><fmt:message key="page.front" /></title>
</head>

<body>
<div id="page.text.left">
    <h1><fmt:message key="page.front.welcome" /></h1>
    <p><fmt:message key="page.front.paragraph.1" /></p>
    <p><fmt:message key="page.front.paragraph.2" /></p>
    <p>
        <fmt:message key="page.front.paragraph.3" />
        <ul>
            <li><a href="http://maven.apache.org/" target="_blanc">Maven</a> <fmt:message key="page.front.maven" /></li>
            <li><a href="http://www.springsource.org/" target="_blanc">Springframwork</a> <fmt:message key="page.front.spring" /></li>
            <li>Open Symphony <a href="http://www.sitemesh.org/" target="_blanc">Sitemesh</a> <fmt:message key="page.front.sitemesh" />
                <fmt:message key="page.front.sitemesh.more" /></li>
            <li><a href="http://www.junit.org/" target="_blanc">JUnit</a> <fmt:message key="page.front.junit" /></li>
            <li><a href="http://www.mockito.org/" target="_blanc">Mockito</a> <fmt:message key="page.front.mockito" /></li>
            <li><a href="http://code.google.com/p/hamcrest/" target="_blanc">Hamcrest</a> <fmt:message key="page.front.hamcrest" /></li>
            <li><a href="http://www.joda.org/" target="_blanc">Joda</a> <fmt:message key="page.front.joda" /></li>
        </ul>
    </p>
</div>
</body>
</html>
