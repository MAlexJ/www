<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

</script>

<!-- div: header -->
<div id="header_create_form">
    <div class="header_class">
        <div class="header_home">
            <a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На главную</a>
        </div>
        <div class="header_back">
            <a href="${CONTEXT}${CURRENT_MAPPING}/termsList.php">Назад</a>
        </div>
        <div class="header_label_2">
            <c:choose>
                <c:when test="${button eq 1}">
                    Для создания Term заполните поле и нажмите кнопку "Создать".
                </c:when>
                <c:otherwise>
                    Для модификации введите новое значение и нажмите кнопку "Применить".
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<!-- div: content -->
<div id="content_create_form">
    <div class="container_form">
        <c:choose>
        <c:when test="${button eq 1}">
        <!-- form: f_creat -->
        <form id="form_creat" name="form_creat" action="${CONTEXT}${CURRENT_MAPPING}/termCreate.php" method="post">
            </c:when>
            <c:otherwise>
            <!-- form: f_creat -->
            <form id="form_creat" name="form_creat" action="${CONTEXT}${CURRENT_MAPPING}/termsModifying.php"
                  method="post">
                </c:otherwise>
                </c:choose>

                <!-- name -->
                <div>
                    <label class="label_form" for="name">Duration (in weeks):</label>
                    <input id="name" class="input_form" type="text" name="name" placeholder="The duration of Term">
                </div>

                <table class="table_discipline" border="2" cellpadding="10">
                    <tr bgcolor="#a9a9a9">
                        <c:if test="${CURRENT_ROLE eq 1}">
                            <td></td>
                        </c:if>
                        <td>Name Disciplines</td>
                    </tr>
                    <c:forEach items="${disciplines}" var="ds">
                        <tr>
                            <c:if test="${CURRENT_ROLE eq 1}">
                                <td><input type='checkbox' id="${ds.id}" value="${ds.id}"></td>
                            </c:if>
                            <td>${ds.name}</td>
                        </tr>
                    </c:forEach>
                </table>
                <!-- submit -->
                <div class="div_form_term">
                    <label class="label_form_term"></label>
                    <c:choose>
                        <c:when test="${button eq 1}">
                            <input class="input_submit" type="submit" value="Создать">
                        </c:when>
                        <c:otherwise>
                            <input class="input_submit" type="submit" value="Применить">
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
    </div>
    <p class="error_message">
        <jsp:include page="../modules/validationMessage.jsp"/>
    </p>
</div>

