<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- div: header -->
<div id="header_create_form">
    <div class="header_class">
        <div class="header_home">
            <a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На главную</a>
        </div>
        <div class="header_back">
            <a href="${CONTEXT}${CURRENT_MAPPING}/disciplinesList.php">Назад</a>
        </div>
        <div class="header_label_2">
            <c:choose>
                <c:when test="${button eq 1}">
                    Для создания Discipline заполните поле и нажмите кнопку "Создать".
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
        <form id="form_creat" name="form_creat" action="${CONTEXT}${CURRENT_MAPPING}/disciplineCreate.php" method="post">
            </c:when>
            <c:otherwise>
            <!-- form: f_creat -->
            <form id="form_creat" name="form_creat" action="${CONTEXT}${CURRENT_MAPPING}/disciplineModifying.php"
                  method="post">

                <!-- id -->
                <input type="hidden" name="id" value="${discipline.id}">

                </c:otherwise>
                </c:choose>


                <!-- name -->
                <div>
                    <label class="label_form" for="name">Discipline</label>
                    <input id="name" class="input_form" type="text" name="name" placeholder="Name Discipline:" value="${discipline.name}">
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
