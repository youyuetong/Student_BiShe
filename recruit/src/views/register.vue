<template>
  <div class="container" style="background-image: url(/static/img/login.53aca80.jpg)">
    <div class="hrForm">
      <div class="choice" ref="choice">
        <p @click="changeTabs(false)">我要找工作</p>
        <p @click="changeTabs(true)">我要招聘</p>
      </div>
      <el-form :model="hrInfo" status-icon :rules="hrrules" ref="hrInfo" label-width="100px" class="hrruleForm">
        <el-form-item  prop="loginName">
          <el-input type="text" v-model="hrInfo.loginName" auto-complete="off" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item  prop="password">
          <el-input type="password" v-model="hrInfo.password" auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item  prop="checkPass">
          <el-input type="password" v-model="hrInfo.checkPass" auto-complete="off" placeholder="确认密码"></el-input>
        </el-form-item>
        <el-form-item prop="mobile">
          <el-input v-model.number="hrInfo.mobile" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="hrInfo.email" placeholder="邮箱"></el-input>
        </el-form-item>
      
        <!-- <el-form-item  prop="name" v-if="isHr == false">
          <el-input type="text" v-model="hrInfo.name" auto-complete="off" placeholder="用户名"></el-input>
        </el-form-item> -->

        <el-form-item prop="gender"  v-if="isHr == false">
          <el-select v-model="hrInfo.gender" placeholder="请选择性别" style="width:100%">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="0"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="collegeName" v-if="isHr == false">
          <el-input v-model="hrInfo.collegeName" placeholder="大学名称"></el-input>
        </el-form-item>

        <el-form-item prop="education" v-if="isHr == false">
          <el-select v-model="hrInfo.education" placeholder="请选择教育水平" style="width: 100%">
            <el-option label="大专" value="大专"></el-option>
            <el-option label="本科" value="本科"></el-option>
            <el-option label="研究生" value="研究生"></el-option>
            <el-option label="博士" value="博士"></el-option>
          </el-select>
        </el-form-item>
        
        <div v-if="isHr">
          <el-form-item prop="company">
              <el-input v-model="hrInfo.enterpriseName" placeholder="企业名称"></el-input>
          </el-form-item>

          <!-- <div @click="changeClick" class="tips" v-if="tipsShow">没有您所在的公司？请添加</div>-->
        </div>

        <el-form-item style="margin-bottom: 50px">
          <el-button type="primary" class="registerBtn" @click="hrSubmit('hrInfo')">注册</el-button>
        </el-form-item>
      </el-form>
      <!-- <div class="introduceCompany">
        <img src="../assets/logo.png" @click="toIndex"/>
         <p>Job-hunting and recruitment oriented to the Internet,
           providing more internship and job opportunities
           From now on, thumb up your life, start with this job.</p>
      </div>
      <div class="footer-tip3" @click="toLogin">
           已有账号?直接登录
    </div> -->
     <div class="footer-tip3" @click="toLogin">
           已有账号?直接登录
    </div>
    </div>
  
<!-- <img class="bg_bottom" src="../assets/bg_bottom.png"/>
<img class="bg_bottom2" src="../assets/bg_bottom2.png"/> -->
  </div>
</template>

<style>
@import "../assets/Animate/animate.min.css";
  html * {
    padding: 0;
    margin: 0;
  }

  * {
    box-sizing: border-box;
  }

  .container {
    width: 100%;
    position: relative;
    height: 100%;
    overflow: hidden;
  }

  .container::before {
    content: '';
    position: absolute;
    filter:blur(1px);
    background-image: url("https://upload-images.jianshu.io/upload_images/9381131-261e4e2e3fca50f5.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
    background-size: 100% 100%;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    transform: matrix(1, 0, 0, 1, 0, 0);
    transition: all 500ms linear 0s;
  }

  .container:hover::before {
    transform: matrix(1.05, 0, 0, 1.05, 3.07, 5.7)
  }

  .choice {
    text-align: left;
    padding-left: 100px;
  }

  .choice p{
    display: inline-block;
    width: 173px;
    height: 30px;
    text-align: center;
    line-height: 30px;
    color: #5a5a5a;
  }

  .choice p:first-child {
    border-right: 1px solid #ededed;
    font-weight: 700;
  }

  .hrForm {
    background: #fff;
    border: 1px solid #ededed;
    width: 555px;
    min-height: 630px;
    margin: 120px auto 150px auto;
    box-shadow: 0px 5px 8px #888;
    border-radius: 8px;
    padding-top: 36px;
    position: relative;
  }

  .el-form-item__content {
    margin-left: 0px !important;
  }

  .hrruleForm {
    width: 500px;
    position: relative;
    top: 14px;
    left: -14px;
    padding: 14px 14px 14px 100px;
  }

  .choose {
    width: 100%;
  }

  .footer-tip3{
    position: absolute;
    bottom: 16px;
    right: 16px;
    cursor: pointer;
    color: rgba(0, 0, 0, 0.5);
  }

  .registerBtn {
    width: 100%;
  }

  .tips {
    margin-top: -20px;
    text-align: left;
    cursor: pointer;
    color: red;
    font-size: 14px;
  }

  .bg_bottom {
    position: fixed;
    bottom: 0;
    right: 0;
  }

  .bg_bottom2 {
    position: fixed;
    bottom: 0;
    left: 0;
  }

  .introduceCompany {
    color: #5f6368;
    font-size: 20px;
    font-weight: bold;
    position: absolute;
    top: 0;
    right: 0;
    width: 320px;
    height: 600px;
    padding: 30px 30px 0 0;
  }

  .introduceCompany img {
    width: 300px;
    height: 300px;;
    margin-bottom: 20px;
    cursor: pointer;
  }

  .companyForm {
    padding: 0 66px;
  }
</style>

<script>/* eslint-disable indent,quotes,space-before-function-paren,brace-style */

import fetch from '../api/fetch'

export default {
  data() {
    
    var checkCompany = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入公司名称"))
      } else {
        return callback()
      }
    }
    var checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入手机号"))
      } else if (!/^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/.test(value)) {
        return callback(new Error("请输入正确的手机号"))
      } else {
        callback()
      }
    }
    var checkEmail = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入邮箱"))
      } else if (!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(value)) {
        return callback(new Error("请输入正确的邮箱"))
      } else {
        callback()
      }
    }
    var validUsername = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"))
      } else if (!/^\w+$/gi.test(value)) {
        callback(new Error("用户名必须由英文或者数字组成"))
      } else {
        callback()
      }
    }
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"))
      } else {
        if (this.hrInfo.checkPass !== "") {
          this.$refs.hrInfo.validateField("checkPass")
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"))
      } else if (value !== this.hrInfo.password) {
        callback(new Error("两次输入密码不一致!"))
      } else {
        callback()
      }
    }
    return {
      msg: "发送验证码",
      count: "",
      timer: null,
      show: true,
      confirmCode: "",
      companyId: "",
      isHr: false,
      options: [],
      dialogShow: false,
      tipsShow: false,
      companyInfo: {
        name: '',
        avatar: '',
        introduce: '',
        scale: '',
        type: ''
      },
      formLabelWidth: '120px',
      hrInfo: {
        password: "",
        checkPass: "",
        mobile: "",
        loginName: "",
        email: "",
        enterpriseName: "",
        education: null,
        collegeName: null,
        gender: null,
        userType: "H",
        name: null
      },
      hrrules: {
        // code: [{validator: checkCode, trigger: "blur"}],
        enterpriseName: [{validator: checkCompany, trigger: "blur"}],
        loginName: [{validator: validUsername, trigger: "blur"}],
        password: [{validator: validatePass, trigger: "blur"}],
        checkPass: [{validator: validatePass2, trigger: "blur"}],
        phone: [{validator: checkPhone, trigger: "blur"}],
        email: [{validator: checkEmail, trigger: "blur"}]
      }
    }
  },

  mounted() {
    // this.getCompany()
    this.addAnimation()
  },

  methods: {
    addAnimation() {
      let form = document.getElementsByClassName('hrForm')[0]
      form.classList.add('animated')
      form.classList.add('bounceInDown')
    },

    hrSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
if(this.isHr){
          this.hrInfo.userType = "E";
        }else{
          this.hrInfo.userType = "H";
        }
console.log(this.hrInfo);
          fetch.hrRegister(this.hrInfo).then(res => {
            let data = JSON.parse(res.data);

            if (res.status === 200) {
              if (data.result) {
                this.$message({
                  message: "注册成功",
                  type: "success"
                })
                this.$router.push({ name: "login" })
              } else {
                this.$message({
                  message: data.message,
                  type: "warning"
                })
              }
            }
          }).catch(e => {
            console.log(e)
          })
        }       
      })
    },

    toLogin() {
      this.$router.push({name: "login"})
    },


    // 控制添加公司弹窗显示
    changeClick() {
      this.dialogShow = true
    },
   
    // 注册切换角色
    changeTabs(isHr) {
      if (!isHr) {
        this.$refs.choice.firstChild.style.fontWeight = '700'
         this.$refs.choice.lastChild.style.fontWeight = '400'
      } else {
        this.$refs.choice.firstChild.style.fontWeight = '400'
        this.$refs.choice.lastChild.style.fontWeight = '700'
      }
      this.isHr = isHr;
      
    },
    toIndex() {
      this.$router.push({name: 'index'})
    }
  }
}
</script>
