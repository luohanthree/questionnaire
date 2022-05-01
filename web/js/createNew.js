let i = 0; //i用于区分不同题目
let t = 1; //用于区分不同选项
let x = 0 //用于选择不同的函数
let parent = document.getElementById('formContent');

let form = {
    type: "",
    options: [],
    question_name: ""
}
let forms = [form]

function changeFunction(y) {
    x = y;
}

function createContent() {
    let question_name = document.getElementById('question');
    let ques_str = question_name.value;
    let tr = document.createElement('tr');
    let td = document.createElement('td');
    let new_td = document.createElement('td');
    tr.setAttribute("id","content" + i);
    parent.appendChild(tr);
    tr.appendChild(td);
    tr.appendChild(new_td);
    td.innerHTML = "<p>" + i + "\t&nbsp;" + ques_str + "</p>";
    i++;
    return new_td;
}

function createQues() {
    forms.push(form);
    let new_td = createContent();
    if (x === 1) {
        new_td.innerHTML = "<button class='btn btn-primary' type='button' data-bs-toggle='modal' data-bs-target='#createRadio'" +" id=btn" + i + ">创建选项</button>";
        forms[i].type = "radio";
    } else if (x === 2) {
        new_td.innerHTML = "<button class='btn btn-primary' type='button' data-bs-toggle='modal' data-bs-target='#createCheckbox'" +" id=btn" + i + ">创建选项</button>";
        forms[i].type = "checkbox";
    } else if (x === 3) {
        new_td.innerHTML = "<button class='btn btn-primary' type='button' data-bs-toggle='modal' onclick='addText()'" +" id=btn" + i + ">创建回答框</button>";
        forms[i].type = "textarea";
    }
    document.getElementById('question').value= '';
    t = 1;
    x = 0
}

function addRadio() {
    addOptions("radio");
}

function addOptions(type) {
    let content;
    let tr = document.createElement('tr');
    let td = document.createElement('td');
    td.setAttribute("colspan","2");
    let ul = document.createElement('ul');
    let li = document.createElement('li');
    let label = document.createElement('label');
    label.setAttribute("for",type + t);
    parent.appendChild(tr);
    tr.appendChild(td)
    td.appendChild(ul);
    ul.appendChild(li);
    if (type === "textarea") {
        content = document.createElement('textarea');
        content.setAttribute("rows","5");
        content.setAttribute("class","form-control");
        label.innerHTML = "请回答";
    } else {
        content = document.createElement('input');
        content.setAttribute("type",type);
        content.setAttribute("class","form-check-input");
        label.innerHTML = t + "\t&nbsp;" + document.getElementById(type).value;
        label.setAttribute("class","form-check-label");
        content.value = document.getElementById(type).value;
        forms[i].options.push(content.value);
        document.getElementById(type).value='';
    }
    content.setAttribute("id",type + t)
    ul.appendChild(li);
    li.appendChild(content);
    li.appendChild(label);
    t++;
}

function addCheckBox() {
    addOptions("checkbox")
}

function addText() {
    addOptions("textarea");
}

function send_to_backend() {
    let jsons = JSON.stringify(forms);
    console.info(jsons);
    $.ajax({
        url: "doCreate.do",
        type: 'post',
        data: {
            jsonStr: jsons,
        },
        success: function (data) {
            alert("创建成功");
        },
        error: function (errorMsg) {
            alert("创建失败")
        }
    })
}
