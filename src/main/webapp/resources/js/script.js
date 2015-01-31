$(document).ready(function () {
    $('#form_creat').validate({

        rules: {
            name: {
                required: true,
                minlength: 3,
                maxlength: 18
            },
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
            name: {
                required: "*Это поле обязательно для заполнения",
                minlength: "*Discipline должена быть минимум из 3 символов",
                maxlength: "*Максимальное число символов - 16"
            },
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
                minlength: "* Введите дату в формате: YYYY-mm-dd"
            }
        }
    });
});




