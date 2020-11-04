<template>
  <div>
    <table v-if="!isEdit" class="container">
      <th> 个人信息</th>
      <tr>
        <td>姓名：</td>
        <td>{{list.name}}</td>
      </tr>
      <tr>
        <td>性别：</td>
        <td>{{list.genderDesc}}</td>
      </tr>
      <tr>
        <td>手机号：</td>
        <td>{{list.mobile}}</td>
      </tr>
      <tr>
        <td>我的简介：</td>
        <td>{{list.selfIntroduction}}</td>
      </tr>
      <tr>
        <td>出生日期：</td>
        <td>{{list.birthDate}}</td>
      </tr>
      <tr>
        <td>学历：</td>
        <td>{{list.education}}</td>
      </tr>
      <tr>
        <td>学校：</td>
        <td>{{list.collegeName}}</td>
      </tr>
      <!-- <tr>
        <td>我想去的公司：</td>
        <td>{{list.lastCompany}}</td>
      </tr>-->
      <tr>
        <td> 我感兴趣的工作：</td>
        <td>{{list.tag}}</td>
      </tr> 


      <tr>
        <td> 教育经历：</td>
        <td>
          <el-table :data="list.educationExList" :row-class-name="tableRowClassName"  style="width: 600px">
            <el-table-column prop="experienceContent"  label="经历" >
            </el-table-column>
            <el-table-column prop="startDate" label="开始时间" >
            </el-table-column>
            <el-table-column prop="endDate"  label="结束时间">
            </el-table-column>
          </el-table>
        </td>
      </tr>  

      <tr>
        <td> 项目经历：</td>
        <td>
          <el-table :data="list.projectExList" :row-class-name="tableRowClassName"  style="width: 600px">
            <el-table-column prop="experienceContent"  label="经历" >
            </el-table-column>
            <el-table-column prop="startDate" label="开始时间" >
            </el-table-column>
            <el-table-column prop="endDate"  label="结束时间">
            </el-table-column>
          </el-table>
        </td>
      </tr>  

      <tr>
        <td> 工作经历：</td>
        <td>
          <el-table :data="list.workExList" :row-class-name="tableRowClassName"  style="width: 600px">
            <el-table-column prop="experienceContent"  label="经历" >
            </el-table-column>
            <el-table-column prop="startDate" label="开始时间" >
            </el-table-column>
            <el-table-column prop="endDate"  label="结束时间">
            </el-table-column>
          </el-table>
        </td>
      </tr>  
  

      <el-button class="edit" @click="changeEdit">编辑</el-button>
    </table>
    <el-form  :model="list" status-icon :rules="rules2" ref="list" label-width="100px" class="formWrap"
             v-if="isEdit">
       <el-form-item label="姓名" prop="name">
        <el-input v-model="list.name" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select v-model="list.gender" placeholder="请选择性别" style="width: 100%">
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="list.mobile"></el-input>
      </el-form-item>
      <el-form-item label="我的简介" prop="selfIntroduction">
        <el-input v-model="list.selfIntroduction"></el-input>
      </el-form-item>
      <el-form-item label="出生日期" prop="birthDate">
          <el-date-picker style="width: 100%"
            v-model="list.birthDate"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="学历" prop="education">
         <el-select v-model="list.education" placeholder="请选择教育水平" style="width: 100%">
          <el-option label="大专" value="大专"></el-option>
          <el-option label="本科" value="本科"></el-option>
          <el-option label="研究生" value="研究生"></el-option>
          <el-option label="博士" value="博士"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学校" prop="collegeName">
        <el-input v-model="list.collegeName"></el-input>
      </el-form-item>

      
      <!-- <el-form-item label="我想去的公司" prop="lastCompany">
        <el-select v-model="list.lastCompany" multiple placeholder="请选择" style="width: 100%">
          <el-option
            v-for="item in companyOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
       </el-select>
      </el-form-item> -->
      <el-form-item label="感兴趣的工作" prop="tags">
        <el-checkbox-group v-model="list.tags" :checked="checked" @change="checked=!checked">
            <el-checkbox v-for="item in skillOptions" :label="item" :key="item">{{item}}</el-checkbox>
        </el-checkbox-group>

        <!-- <el-select v-model="intentionJob" multiple placeholder="请选择" style="width: 100%">
          <el-option
            v-for="item in skillOptions"
            :key="item"
            :label="item"
            :value="item">
        </el-option>
       </el-select>  -->
      </el-form-item>

      <el-form-item label="教育经历" prop="educationExList" style="width: 700px">
        <el-button class='editor' @click="addProjectItem('JY')" style="float:left">新增</el-button>
        <el-table :data="list.educationExList" :row-class-name="tableRowClassName"  style="width: 600px">
          <el-table-column prop="experienceContent"  label="经历" >
          </el-table-column>
          <el-table-column prop="startDate" label="开始时间" >
          </el-table-column>
          <el-table-column prop="endDate"  label="结束时间">
          </el-table-column>
          <el-table-column prop="endDate" label="操作">
                <template slot-scope="scope">
             <el-button @click="delExItem(scope.row)" type="text" size="small">删除</el-button>
                </template>
          </el-table-column>
        </el-table>
      </el-form-item>

      <el-form-item label="项目经历" prop="projectExList" style="width: 700px">
        <el-button class='editor' @click="addProjectItem('XM')" style="float:left">新增</el-button>
        <el-table :data="list.projectExList" :row-class-name="tableRowClassName"  style="width: 600px">
          <el-table-column prop="experienceContent"  label="经历" >
          </el-table-column>
          <el-table-column prop="startDate" label="开始时间" >
          </el-table-column>
          <el-table-column prop="endDate"  label="结束时间">
          </el-table-column>
          <el-table-column prop="endDate" label="操作">
                <template slot-scope="scope">
             <el-button @click="delExItem(scope.row)" type="text" size="small">删除</el-button>
                </template>
          </el-table-column>
        </el-table>
      </el-form-item>

      <el-form-item label="工作经历" prop="projectExList" style="width: 700px">
        <el-button class='editor' @click="addProjectItem('GZ')" style="float:left">新增</el-button>
        <el-table :data="list.workExList" :row-class-name="tableRowClassName"  style="width: 600px">
          <el-table-column prop="experienceContent"  label="经历" >
          </el-table-column>
          <el-table-column prop="startDate" label="开始时间" >
          </el-table-column>
          <el-table-column prop="endDate"  label="结束时间">
          </el-table-column>
          <el-table-column prop="endDate" label="操作">
                <template slot-scope="scope">
             <el-button @click="delExItem(scope.row)" type="text" size="small">删除</el-button>
                </template>
          </el-table-column>
        </el-table>
      </el-form-item>


      <el-dialog title="经历填写" :visible.sync="experienceDialogFlag" :modal-append-to-body="false">
        <el-form :model="experienceForm">
          <el-form-item label="经历内容" label-width="120px">
            <el-input v-model="experienceForm.experienceContent" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="经历时间范围" label-width="120px">
            <el-date-picker style="width: 100%"
              v-model="experienceForm.startEndDateRange"
              type="daterange"
              format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancelExItemDialog">取 消</el-button>
          <el-button type="primary" @click="saveExItem">确 定</el-button>
        </div>
      </el-dialog>

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

  import fetch from '../api/fetch'

  export default {
    props: ['list', 'imageUrl'],
    data () {
      var checkname = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('姓名不能为空'))
        } else {
          callback()
        }
      }
      var checksex = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('性别不能为空'))
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
      // var checkendTime = (rule, value, callback) => {
      //   if (!value) {
      //     return callback(new Error('毕业年份不能为空'))
      //   } else {
      //     callback()
      //   }
      // }
      var checkschool = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('学校不能为空'))
        } else {
          callback()
        }
      }
      // var checkcompany = (rule, value, callback) => {
      //   if (this.intentionCompany.length === 0) {
      //     return callback(new Error('想去的公司不能为空'))
      //   } else {
      //     callback()
      //   }
      // }
      // var checkaddress = (rule, value, callback) => {
      //   if (!value) {
      //     return callback(new Error('地址不能为空'))
      //   } else {
      //     callback()
      //   }
      // }
      // var checkjob = (rule, value, callback) => {
      //   if (this.intentionJob.length === 0) {
      //     return callback(new Error('感兴趣的工作不能为空'))
      //   } else {
      //     callback()
      //   }
      // }
      var checkeducation = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('学历不能为空'))
        } else {
          callback()
        }
      }
      return {
        checked: false,
        isEdit: true,
        rules2: {
          name: [{validator: checkname, trigger: 'blur'}],
          gender: [{validator: checksex, trigger: 'blur'}],
          // mobile: [{validator: checkaddress, trigger: 'blur'}],
          selfIntroduction: [{validator: checkintroduce, trigger: 'blur'}],
          // birthDate: [{validator: checkendTime, trigger: 'blur'}],
          education: [{validator: checkeducation, trigger: 'blur'}],
          collegeName: [{validator: checkschool, trigger: 'blur'}],
          // lastCompany: [{validator: checkcompany, trigger: 'blur'}],
          // skills: [{validator: checkjob, trigger: 'blur'}]
        },

        // experienceRule: {
        //   experienceType: [{validator: checkname, trigger: 'blur'}],
        //   startEndDateRange: [{validator: checkeducation, trigger: 'blur'}],
        // },
        options: [
          {
            value: '2019',
            label: '2019'
          },
          {
            value: '2020',
            label: '2020'
          },
          {
            value: '2021',
            label: '2021'
          },
          {
            value: '2022',
            label: '2022'
          },
          {
            value: '2023',
            label: '2023'
          }
        ],
        companyOptions: [],
        skillOptions: [],
        intentionCompany: [],
        intentionJob: [],

        experienceForm: {
          experienceType: null,
          experienceContent: null,
          startEndDateRange: ''
        },
        experienceDialogFlag: false,
        experienceDialogType: "",
         tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
      }
    },
    mounted() {
      // this.getCompanyName()
      this.getJobName()
    },
    watch: {
      list(val, oldVal) {
        if (val !== oldVal) {
          //  this.intentionCompany = this.list.intentionCompany.split(',') || ''
          //  this.intentionJob = this.list.intentionJob.split(',') || ''

          this.list.tags = this.list.tag.split(',') || ''
        }
      }
    },
    methods: {
      changeEdit () {
        this.isEdit = !this.isEdit
      },
      // 提交
      submitInfo (formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            // this.list.intentionCompany = this.intentionCompany.toString();
            // this.list.intentionJob = this.intentionJob.toString();

            this.list.tag = this.list.tags.join(",");
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
      // 获取公司名称
      getCompanyName () {
        fetch.getComName().then(res => {
          if (res.data.code === 0) {
            this.companyOptions = res.data.data.map(e => ({ value: e.name, label: e.name }));
          }
        }).catch(e => {
          console.log('err', e)
        })
      },
      // 获取职位名称
      getJobName () {
        let skills = [
          "JAVA", ".net", "android", "ios", "node", "python", 
          "vue", "angular", "C", "C++", "Go", "Linux Shell", "Jquery"
        ]

        // skills.forEach(element=>{
        //   this.skillOptions.push(element);
        // })
        this.skillOptions = skills;
        console.log(this.skillOptions);

        // fetch.getRecommandJob().then(res => {
        //   if(res.status === 200) {
        //     const nameList = res.data.data;
        //     for (let i = 0; i < nameList.length; i++) {
        //       const object = {};
        //       object.value = nameList[i];
        //       object.label = nameList[i];
        //       this.jobOptions.push(object);
        //     }
        //   }
        // })
      },

      handleCheckedCitiesChange(value) {
        console.log(value);
        // this.checkAll = checkedCount === this.cities.length;
        // this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
      },

      addProjectItem(experienceType){
        let _this = this;
        if(experienceType == 'XM'){
          _this.experienceDialogType = "项目经历";
        }else if(experienceType == 'GZ'){
          _this.experienceDialogType = "工作经历";
        }else if(experienceType == 'JY'){
          _this.experienceDialogType = "教育经历";
        }
        _this.experienceDialogFlag = true;
        _this.experienceForm.experienceType = experienceType;
      },
      cancelExItemDialog(){
        this.experienceDialogFlag = false;
        this.experienceForm = {
          experienceType: null,
          experienceContent: null,
          startEndDateRange: ''
        }
      },
      // 年月日，时分秒
getFullTime(date) {
    let Y = date.getFullYear() + '';
    let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1);
    let D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate());
    return Y + "-" + M + "-" + D;
},
delExItem(item){
  console.log(item);
  item.index
  if(item.experienceType == 'XM'){
    this.list.projectExList.splice(item.index, 1);
  }else if(item.experienceType == 'GZ'){
     this.list.workExList.splice(item.index, 1);
  }else if(item.experienceType == 'JY'){
     this.list.educationExList.splice(item.index, 1);
  }
},
tableRowClassName ({row, rowIndex}) {
  debugger;
  //把每一行的索引放进row
  row.index = rowIndex;
},
// rowClick(row, event, column) {
//   debugger;
//   if (row != null) {
//     this.selection = row;
//   }
// },
      saveExItem(){
        let _this = this;
        console.log(_this.experienceForm);
        let range = _this.experienceForm.startEndDateRange;
        console.log(range);
        let startDate = _this.getFullTime(range[0]);
        let endDate = _this.getFullTime(range[1]);

        let obj = {
          experienceType: _this.experienceForm.experienceType,
          experienceContent: _this.experienceForm.experienceContent,
          startDate: startDate,
          endDate: endDate
        }
        console.log(obj);
        console.log(_this.list);
        console.log(_this.list.projectExList);
        if(_this.experienceForm.experienceType == 'XM'){
          if(typeof(_this.list.projectExList) == 'undefined'){
              _this.list.projectExList = [];
              _this.list.projectExList.push(obj);
          }else{
              _this.list.projectExList.push(obj);
          }
        }else if(_this.experienceForm.experienceType == 'GZ'){
           if(typeof(_this.list.workExList) == 'undefined'){
              _this.list.workExList = [];
              _this.list.workExList.push(obj);
          }else{
               _this.list.workExList.push(obj);
          }

         
        }else if(_this.experienceForm.experienceType == 'JY'){
           if(typeof(_this.list.educationExList) == 'undefined'){
              _this.list.educationExList = [];
              _this.list.educationExList.push(obj);
          }else{
               _this.list.educationExList.push(obj);
          }
        }
   console.log(_this.list);
        _this.cancelExItemDialog();
      }
    }
  }
</script>
