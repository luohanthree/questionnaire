let i = 0; //i用于区分不同题目
let t = 1; //用于区分不同选项
let x = 0 //用于选择不同的函数
let m = 0;
let parent = document.getElementById('formContent');
let options = [];
let types = [];
let question_names = [];
let options_num = [];


//设置问题的序号
function changeFunction(y) {
    x = y;
}
//创建单个问题的容器
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
    question_names.push(ques_str);
    return new_td;
}

function createQues() {
    options_num.push(t);
    let new_td = createContent();
    if (x === 1) {
        new_td.innerHTML = "<button class='btn btn-primary' type='button' data-bs-toggle='modal' data-bs-target='#createRadio'" +" id=btn" + i + ">创建选项</button>";
        types.push("radio");
    } else if (x === 2) {
        new_td.innerHTML = "<button class='btn btn-primary' type='button' data-bs-toggle='modal' data-bs-target='#createCheckbox'" +" id=btn" + i + ">创建选项</button>";
        types.push("checkbox");
    } else if (x === 3) {
        new_td.innerHTML = "<button class='btn btn-primary' type='button' data-bs-toggle='modal' onclick='addText()'" +" id=btn" + i + ">创建回答框</button>";
        types.push("text");
    }
    document.getElementById('question').value= '';
    t=1;
    x=0
    i++;
}

function addRadio() {

    addOptions("radio");
}

function addCheckBox() {
    addOptions("checkbox")
}

function addText() {
    addOptions("textarea");
}


//添加选项或者文本框，通过相应函数调用
//关联函数，addRadio(),addCheckBox(),addText()
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
        options.push("");
    } else {
        content = document.createElement('input');
        content.setAttribute("type",type);
        content.setAttribute("class","form-check-input");
        label.innerHTML = t + "\t&nbsp;" + document.getElementById(type).value;
        label.setAttribute("class","form-check-label");
        let value = document.getElementById(type).value;
        options.push(value);
        document.getElementById(type).value='';

    }
    m++;
    content.setAttribute("id",type + t)
    ul.appendChild(li);
    li.appendChild(content);
    li.appendChild(label);
    t++;
}

//发送数据给后端
function send_to_backend() {
    let formName = document.getElementById('formName').value;
    let z = 0;
    console.log(options)
    let questionnaire = [];
    for (let temp = 0;temp < i; temp++) {
        let question = {};
        question.options = [];
        for (let n = 0; n <= options_num[temp] + 1; n++) {
            question.options.push(options[z]);
            z++;
        }
        question.options = question.options.filter(n => n);
        question.type = types[temp];
        question.question_name = question_names[temp];
        questionnaire.push(question);
    }
    let questionnaire_json = JSON.stringify(questionnaire);
    console.info(questionnaire_json);
    if (formName === '') {
        alert("问卷标题不得为空");
    } else {
        $.ajax({
            url: "doCreate.do",
            type: 'post',
            data: {
                jsonStr: questionnaire_json,
                formName : formName
            },
            dataType:'json',
            success: function (data) {
                alert("创建成功,点击全部问卷查看!");
                window.location.reload();
            },
            error: function (errorMsg) {
                alert("创建失败")
            }
        })
    }
}
