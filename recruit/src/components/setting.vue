<template>
  <div>
    <el-collapse>
      <!-- <el-collapse-item title="修改手机号" name="1" class="set">
        <el-form :model="phoneForm" status-icon :rules="phonerules" ref="phoneForm" label-width="100px"
                 class="demo-ruleForm">
          <el-form-item label="手机号" prop="phone" class="settinginput">
            <el-input v-model="phoneForm.phone" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn" @click="changePhone('phoneForm')">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item> -->
      <el-collapse-item title="修改密码" name="2" class="set">
        <el-form :model="passForm" status-icon :rules="passwordrules" ref="passForm" label-width="100px"
                 class="demo-ruleForm">
          <el-form-item label="原密码" prop="oldPassword" class="settinginput">
            <el-input type="password" v-model="passForm.oldPassword" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword" class="settinginput">
            <el-input type="password" v-model="passForm.newPassword" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkpass" class="settinginput">
            <el-input type="password" v-model="passForm.checkpass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn" @click="changePass('passForm')">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>
      <el-collapse-item title="修改邮箱" name="3" class="set" v-if="isHunter">
        <el-form :model="emailForm" status-icon :rules="emailrules" ref="emailForm" label-width="100px"
                 class="demo-ruleForm">
          <el-form-item label="邮箱" prop="email" class="settinginput">
            <el-input v-model="emailForm.email" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn" @click="changeEmail('emailForm')">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>
       <el-collapse-item title="修改微信ID" name="4" class="set" v-if="isHunter || isHr">
        <el-form :model="wechatForm" status-icon :rules="wechatrules" ref="wechatForm" label-width="100px"
                 class="demo-ruleForm">
          <el-form-item label="微信ID" prop="email" class="settinginput">
            <el-input v-model="wechatForm.wechatId" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn" @click="changeWechatId('wechatForm')">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>
<style>
  .demo-ruleForm {
    margin-top: 20px
  }

  .set {
    margin-top: 21px;
    font-size: 14px;
    color: #909399
  }

  .btn {
    position: relative;
    top: -62px;
    left: 225px;
  }

  .settinginput {
    width: 75%
  }
</style>

<script>/* eslint-disable indent */

  import fetch from '../api/fetch'

  export default {
    data () {
      var checkphone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入正确的手机号'))
        } else {
          callback()
        }
      }

      var checkOldpass = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('密码不能为空'))
        } else {
          callback()
        }
      }

      var checkpass = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('密码不能为空'))
        } else {
          callback()
        }
      }
      var confirmPass = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('密码不能为空'))
        } else if(value != this.passForm.newPassword) {
          return callback(new Error('两次密码不相同'))
        }else {
          callback()
        }
      }
      var checkemail = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入正确的邮箱'))
        } else {
          callback()
        }
      }
      var checkwechatid = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入正确的微信号'))
        } else {
          callback()
        }
      }
      return {
        phoneForm: {
          phone: ''
        },
        passForm: {
          oldPassword: '',
          newPassword: '',
          checkpass: ''
        },
        emailForm: {
          email: ''
        },
        wechatForm: {
          wechatId: ''
        },
        phonerules: {
          phone: [{validator: checkphone, trigger: 'blur'}]
        },
        passwordrules: {
          oldPassword:  [{validator: checkOldpass, trigger: 'blur'}],
          newPassword: [{validator: checkpass, trigger: 'blur'}],
          checkpass: [{validator: confirmPass, trigger: 'blur'}
          ]
        },
        emailrules: {
          email: [{validator: checkemail, trigger: 'blur'}]
        },
        wechatrules: {
          wechatId: [{validator: checkwechatid, trigger: 'blur'}]
        }
      }
    },
    mounted() {
      
    },
    computed: {
      isHr() {
        if(localStorage.getItem('role') === 'E') {
        return true
      }
      return false
      },
      isHunter() {
        if(localStorage.getItem('role') === 'H') {
        return true
      }
      return false
      },
        isAdmin() {
          if(localStorage.getItem('role') === 'A') {
          return true
        }
        return false
        },
    },
    methods: {

      changePhone (formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            fetch.changePhone(this.phoneForm.phone).then(res => {
              if (res.status === 200) {
                if (res.data.code === 0) {
                  this.$message({
                    message: '修改成功',
                    type: 'success'
                  })
                } else {
                  this.$message({
                    message: '修改失败',
                    type: 'success'
                  })
                }
              }
            }).catch(e => {
              console.log(e)
            })
          } else {
            console.log('error submit!!')
          }
        })
      },
      changePass (formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            // delete this.passForm.checkpass
            // const { newPassword, checkpass} = this.passForm;
            console.log(this.passForm.oldPassword);
            let param = {
              "newPassword": this.passForm.newPassword,
              "oldPassword": this.passForm.oldPassword
            }

            if (this.passForm.newPassword === this.passForm.checkpass) {
              fetch.changePass(param).then(res => {
                 let data = JSON.parse(res.data);
                 console.log(data);
                if (data.flag) {
                  this.$message({
                    message: '修改成功',
                    type: 'success'
                  })
                }else{
                  this.$message({
                    message: data.message,
                    type: 'error'
                  })
                }
              }).catch(e => {
                console.log(e)
              })
            } else {
              this.$message({
                message: '两次密码不相同',
                type: 'success'
              })
            }
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      changeEmail (formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            fetch.changeEmail(this.emailForm.email).then(res => {
              let data = JSON.parse(res.data);
              console.log(data);
              if (data.flag) {
                this.$message({
                  message: '修改成功',
                  type: 'success'
                })
              }else{
                this.$message({
                  message: '修改失败',
                  type: 'error'
                })
              }
            }).catch(e => {
              console.log(e)
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      changeWechatId (formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            fetch.changeWechatId(this.wechatForm.wechatId).then(res => {
              let data = JSON.parse(res.data);
              console.log(data);
              if (data.flag) {
                this.$message({
                  message: '修改成功',
                  type: 'success'
                })
              }else{
                this.$message({
                  message: '修改失败',
                  type: 'error'
                })
              }
            }).catch(e => {
              console.log(e)
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      }
    },
    

  }
</script>
