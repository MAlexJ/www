function modifingStudents() {
	var items = $("input[type=checkbox]:checked");
	if (items.length == 0) {
        alert("Please select students");
        return;
    }

    if (items.length > 1) {
        alert("Please select anly one student");
        return;
    }
	var id = $(items[0]).attr("id");

	console.log(id);
	console.log("id=" + id);
	var form = '<form id="modifingStudentForm" action="'
			+ context
			+ '/admin/modifingStudent.php" method="get"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#modifingStudentForm input').val(id);
	$('#modifingStudentForm').submit();

}

$(document).ready(function(){

    $('#form_creat').validate({

        rules: {
            last_name: {
                required: true,
                minlength: 3,
                maxlength: 16
            },
            first_name: {
                required: true,
                minlength: 3,
                maxlength: 16
            },
            group: {
                required: true,
                maxlength: 16
            },
            date: {
                required: true,
                minlength: 10,
                date: true
            }
        },

        messages: {
            last_name: {
                required: "*Это поле обязательно для заполнения",
                minlength: "*Фамилия должена быть минимум из 3 символов",
                maxlength: "*Максимальное число символов - 16"
            },
            first_name: {
                required: "*Это поле обязательно для заполнения",
                minlength: "*Имя должено быть минимум из 3 символов",
                maxlength: "*Максимальное число символов - 16"
            },
            group: {
                required: "*Это поле обязательно для заполнения",
                maxlength: "*Максимальное число символов - 16"
            },
            date: {
                required: "*Это поле обязательно для заполнения",
                minlength: "* Введите даьу в формате: YYYY/mm/dd"
            }

        }
    });


});
