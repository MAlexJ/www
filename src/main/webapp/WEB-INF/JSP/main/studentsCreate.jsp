<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- div: header -->
<div id="header_create_form">
    <div class="header_class">
        <div class="header_home">
            <a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На главную</a>
        </div>
        <div class="header_back">
            <a href="${CONTEXT}${CURRENT_MAPPING}/studentsList.php">Назад</a>
        </div>
        <div class="header_label_2">
            <c:choose>
                <c:when test="${button eq 1}">
                    Для создания студетна заполните все поля и нажмите кнопку "Создать".
                </c:when>
                <c:otherwise>
                    Для модификации введите новые значения и нажмите кнопку "Применить".
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
        <form id="form_creat" name="form_creat" action="${CONTEXT}${CURRENT_MAPPING}/studentsCreate.php" method="post">
            </c:when>
            <c:otherwise>
            <!-- form: f_creat -->
            <form id="form_creat" name="form_creat" action="${CONTEXT}${CURRENT_MAPPING}/studentsModifying.php"
                  method="post">

                <!-- id -->
                <input type="hidden" name="id" value="${student.id }">

                </c:otherwise>
                </c:choose>


                <!-- last_name -->
                <div>
                    <label class="label_form" for="last_name">Фамилия</label>
                    <input id="last_name" class="input_form" type="text" name="last_name" placeholder="Last name:"
                           value="${student.first_name }">
                </div>

                <!-- first_name -->
                <div>
                    <label class="label_form" for="first_name">Имя</label>
                    <input id="first_name" class="input_form" type="text" name="first_name" placeholder="First name:"
                           value="${student.name}">
                </div>

                <!-- group -->
                <div>
                    <label class="label_form" for="group">Группа</label>
                    <input id="group" class="input_form" type="text" name="group" placeholder="Group: "
                           value="${student.group}">
                </div>

                <!-- date -->
                <div>
                    <label class="label_form" for="datepicker">Дата поступления</label>
                    <input id="datepicker" class="input_form" type="date" name="date" placeholder="YYYY-mm-dd "
                           value="${student.date}">
                </div>

                <!-- submit -->
                <div>
                    <label class="label_form"></label>
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
</div>
