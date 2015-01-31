<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        $("#modifyingDiscipline").on("click", function () {
            var listCheckbox = $.map($(":checkbox:checked"), function (el) {
                return $(el).val();
            });
            if (listCheckbox.length == 0) {
                alert("Please select discipline");
                return;
            }
            if (listCheckbox.length > 1) {
                alert("Please select only one discipline");
                return;
            } else {
                $('.admin_table_disc').append('<form id="form_modif" method="GET" action="${CONTEXT}${CURRENT_MAPPING}/disciplineModifying.php">   <input type="hidden" name="listCheckbox" value=' + listCheckbox + '>  </form>');
                $('#form_modif').submit();
            }
        });
    });

    $(document).ready(function () {
        $("#createDiscipline").on("click", function () {
            $('.admin_table_disc').append('<form id="form_modif" method="GET" action="${CONTEXT}${CURRENT_MAPPING}/disciplineCreate.php"> </form>');
            $('#form_modif').submit();
        });
    });

    $(document).ready(function () {
        $("#deleteDisciplines").on("click", function () {
            var listCheckbox = $.map($(":checkbox:checked"), function (el) {
                return $(el).val();
            });

            if (listCheckbox.length != 0) {
                $('.admin_table_disc').append('<form id="form_modif" method="POST" action="${CONTEXT}${CURRENT_MAPPING}/disciplineDelete.php">   <input type="hidden" name="listCheckbox" value=' + listCheckbox + '>  </form>');
                $('#form_modif').submit();
                return;
            }
            alert("Please select students");

        });
    });
</script>

<div class="header_create_form">
    <div class="header_home_1" align="left">
        <a href="${CONTEXT}${CURRENT_MAPPING}/home.php">Назад</a>
    </div>
</div>

<section id="content_style">
    <div> List Disciplines</div>

    <form>
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
    </form>

    <c:if test="${CURRENT_ROLE eq 1}">
        <div class="admin_table_disc">
            <table>
                <tr>
                    <td>
                        <input id="createDiscipline" class="button_style" type="submit" value="Create Discipline">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="modifyingDiscipline" class="button_style" type="submit"
                               value="Modifying select Discipline">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="deleteDisciplines" class="button_style" type="submit"
                               value="Delete select Discipline">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p class="error_message">
                            <jsp:include page="../modules/validationMessage.jsp"/>
                        </p>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>

</section>