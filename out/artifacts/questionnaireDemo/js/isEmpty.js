
function check(myForm) {
    if (myForm.userName.value === '') {
        alert("用户名不能为空!");
        myForm.userName.focus();
        return false;
    } else if (myForm.pwd.value === '') {
        alert("密码不能为空!");
        myForm.pwd.focus();
        return false;
    } else {
        return true;
    }
}