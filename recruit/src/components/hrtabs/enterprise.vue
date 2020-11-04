<template>
  <div>
    <table v-if="!isEdit" class="container">
      <th> 企业信息</th>
      <tr>
        <td>企业名称：</td>
        <td>{{list.name}}</td>
      </tr>
      <tr>
        <td>企业简介：</td>
        <td>{{list.introduction}}</td>
      </tr>
      <tr>
        <td>联系方式：</td>
        <td>{{list.mobile}}</td>
      </tr>
      <tr>
        <td>成立时间：</td>
        <td>{{list.birthDate}}</td>
      </tr>
      <tr>
        <td>企业地址：</td>
        <td>{{list.address}}</td>
      </tr>
      <tr>
        <td>企业城市：</td>
        <td>{{list.city}}</td>
      </tr>
      <tr>
        <td>企业区域：</td>
        <td>{{list.region}}</td>
      </tr>
      <tr>
        <td>微信ID：</td>
        <td>{{list.wechatId}}</td>
      </tr>

      <tr>
        <td>企业logo</td>
        <td><el-image :src="list.logoUrl" style="width: 150px; height: 150px"></el-image></td>
      </tr>
      
      <el-button class="edit" @click="changeEdit">编辑</el-button>
    </table>
    <el-form  :model="list" status-icon :rules="rules2" ref="list" label-width="100px" class="formWrap"
             v-if="isEdit">
       <el-form-item label="企业名称" prop="name">
        <el-input v-model="list.name" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="企业简介" prop="introduction">
         <el-input v-model="list.introduction" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="mobile">
        <el-input v-model="list.mobile"></el-input>
      </el-form-item>
      <el-form-item label="企业地址" prop="address">
        <el-input v-model="list.address"></el-input>
      </el-form-item>
      <el-form-item label="创立时间" prop="birthDate">
          <el-date-picker
            v-model="list.birthDate" style="width:100%"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
      </el-form-item>
      
      <el-form-item label="城市" prop="city">
        <el-input v-model="list.city"></el-input>
      </el-form-item>
      <el-form-item label="区域" prop="region">
        <el-input v-model="list.region"></el-input>
      </el-form-item>
      
        <el-form-item label="微信ID" prop="wechatId">
        <el-input v-model="list.wechatId"></el-input>
      </el-form-item>


 <el-form-item label="企业logo" prop="logoUrl">
        <el-input v-model="list.logoUrl"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button class='editor' @click="changeEdit">返回</el-button>
        <el-button  @click="submitInfo('list')">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<style>
  table {
   width: 600px;
   font-size: 16px
  }

  table tr td {
    padding: 11.2px;
    text-align: left;
  }

  .formWrap {
    width: 500px;
    font-size: 18px;
  }

  .edit {
    margin:10px auto auto 40px;
  }
</style>

<script>/* eslint-disable indent */

  import fetch from '../../api/fetch'

  export default {
    props: ['list'],
    data () {
      var checkname = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('企业名称不能为空'))
        } else {
          callback()
        }
      }
    
      var checkintroduce = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('简介不能为空'))
        } else {
          callback()
        }
      }
     
      // var checkeducation = (rule, value, callback) => {
      //   if (!value) {
      //     return callback(new Error('学历不能为空'))
      //   } else {
      //     callback()
      //   }
      // }
      return {
        isEdit: true,
        rules2: {
          name: [{validator: checkname, trigger: 'blur'}],
          // gender: [{validator: checksex, trigger: 'blur'}],
          // mobile: [{validator: checkaddress, trigger: 'blur'}],
          introduction: [{validator: checkintroduce, trigger: 'blur'}],
          // birthDate: [{validator: checkendTime, trigger: 'blur'}],
          // education: [{validator: checkeducation, trigger: 'blur'}],
          // collegeName: [{validator: checkschool, trigger: 'blur'}],
          // lastCompany: [{validator: checkcompany, trigger: 'blur'}],
          // skills: [{validator: checkjob, trigger: 'blur'}]
        },
        list: null
      }
    },
    mounted() {
      // this.getCompanyName()
      this.getEnterpriseInfo();
    },
    watch: {
      
    },
    methods: {
      changeEdit () {
        this.isEdit = !this.isEdit
      },

      getEnterpriseInfo () {
        fetch
          .getEnterpriseInfo()
          .then(res => {
            let data = JSON.parse(res.data);
            this.list = data.enterpriseInfo !== null ? data.enterpriseInfo : this.list

            console.log("企业信息")
            console.log(this.list);
          })
          .catch(err => {
            console.log(err)
          })
      },

      // 提交订单信息
      submitInfo (formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            // this.list.intentionCompany = this.intentionCompany.toString();
            // this.list.intentionJob = this.intentionJob.toString();
            let param = {
              "basic": this.basic,
              "detail": this.list
            }
            console.log(param);
            fetch
              .putUserInfo(param)
              .then(res => {
                // console.log('list', this.list)
                let data = JSON.parse(res.data);
                console.log(res);
                if (data.flag) {
                  this.$message({
                    message: '保存成功',
                    type: 'success'
                  })
                }else{
                  this.$message({
                    message: '保存失败',
                    type: 'error'
                  })
                }
              })
              .catch(e => {
                console.log(e)
              })
          } else {
            console.log('error submit!!')
          }
        })
      },
    }
  }
</script>
