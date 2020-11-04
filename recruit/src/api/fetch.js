import api from './index'
import axios from 'axios'

let loginHeaders = {
  'Content-Type': 'application/json'
}
export default {
  getHeader () {
    return {
      'Content-Type': 'application/json',
      'token': localStorage.getItem('token')
    }
  },
  getCompany (param) {
    
    return axios.post(api.getCompany(), {draw:1, start: 1, length: 10, condition: param}, {headers: this.getHeader()})
  },
  userRegister (info) {
    return axios.post(api.userRegister(), JSON.stringify(info), {headers: this.getHeader()})
  },
  getComName () {
    return axios.get(api.getComName(), {headers: this.getHeader()}) //headers
  },
  userLogin (info) {
    return axios.post(api.userLogin(), JSON.stringify(info), {headers: loginHeaders})
  },
  getUserInfo () {
    return axios.get(api.getUserInfo(), {headers: this.getHeader()}) //headers
  },
  sendCode (email) {
    return axios.post(api.sendCode(), {email: email}, {headers: this.getHeader()})
  },
  logout () {
    return axios.get(api.logout(), {headers: this.getHeader()})
  },
  sendResume (resumeInfo) {
    return axios.post(api.sendResume(), JSON.stringify(resumeInfo), {headers: this.getHeader()})
  },
  deliveryList (param, start) {
    console.log(start);
    console.log(start);
    if(start == null || typeof(start) == 'undefined'){
      start = 1;
    }
    return axios.post(api.deliveryList(), {draw:1, start: start, length: 10, condition: param}, {headers: this.getHeader()})
  },
  putUserInfo (userInfo) {
    return axios.post(api.putUserInfo(), JSON.stringify(userInfo), {headers: this.getHeader()})
  },
  changePhone (phone) {
    return axios.put(api.changePhone(), {mobile: phone}, {headers: this.getHeader()})
  },
  changePass (password) {
    return axios.post(api.changePass(), {oldPassword: password.oldPassword, newPassword: password.newPassword}, {headers: this.getHeader()})
  },
  changeEmail (email) {
    return axios.post(api.changeEmail(), {email: email}, {headers: this.getHeader()})
  },
  changeWechatId (wechatId){
    return axios.post(api.changeWechatId(), {wechatId: wechatId}, {headers: this.getHeader()})
  },
  getCompanyDetail (id) {
    return axios.get(api.getCompanyDetail(id), {headers: this.getHeader()})
  },
  getResume (userId) {
    return axios.get(api.getResume(userId), {headers: this.getHeader()})
  },
  getJobDetail (id) {
    return axios.get(api.getJobDetail(id), {headers: this.getHeader()})
  },
  deliveryReusme (id) {
    return axios.get(api.deliveryReusme(id), {headers: this.getHeader()})
  },
  getMessage () {
    return axios.get(api.getMessage(), {headers: this.getHeader()})
  },
  findJob (param, start) {
    console.log(start);

    console.log(start);
    if(start == null || typeof(start) == 'undefined'){
      start = 1;
    }

    return axios.post(api.findJob(), {draw:1, start: start, length: 10, condition: param}, {headers: this.getHeader()})
  },
  readMessage (info) {
    return axios.post(api.readMessage(), JSON.stringify(info), {headers: this.getHeader()})
  },
  receiveResume (param, start) {
    console.log(start);
    if(start == null || typeof(start) == 'undefined'){
      start = 1;
    }

    return axios.post(api.receiveResume(), {draw:1, start: start, length: 10, condition: param}, {headers: this.getHeader()})
  },
  saveJob (jobinfo) {
    return axios.post(api.saveJob(), JSON.stringify(jobinfo), {headers: this.getHeader()})
  },

  publishJob(id) {
    return axios.get(api.publishJob(id),  {headers: this.getHeader()})
  },
  unpublishJob(id) {
    return axios.get(api.unpublishJob(id),  {headers: this.getHeader()})
  },
  checkJob () {
    return axios.get(api.checkJob(), {headers: this.getHeader()})
  },
  deletejob (id) {
    return axios.get(api.deletejob(id), {headers: this.getHeader()})
  },
  hrRegister (hrInfo) {
    return axios.post(api.hrRegister(), JSON.stringify(hrInfo), {headers: this.getHeader()})
  },
  verifyMessage (authCode) {
    return axios.post(api.verifyMessage(), JSON.stringify(authCode), {headers: this.getHeader()})
  },
  recommendCandidate () {
    return axios.get(api.recommendCandidate(), {headers: this.getHeader()})
  },
  recommendJob () {
    return axios.get(api.recommendJob(), {headers: this.getHeader()})
  },
  getRecommandJob () {
    return axios.get(api.getRecommandJob(), {headers: this.getHeader()})
  },
  // 添加公司
  addCompany (companyInfo) {
    return axios.post(api.getCompany(), JSON.stringify(companyInfo), {headers: this.getHeader()})
  },

  // 邀请面试
  invitation (id, param) {
    return axios.post(api.invitation(id), JSON.stringify(param), {headers: this.getHeader()})
  },
  // 不邀请面试
  uninvitation (id) {
    return axios.post(api.uninvitation(id), null, {headers: this.getHeader()})
  },
  
  // 接受邀请
  receiveInterview (id, status) {
    return axios.post(api.receiveInterview(id, status), null, {headers: this.getHeader()})
  },

  //通过面试
  passInterview (id, status) {
    return axios.post(api.passInterview(id, status), null, {headers: this.getHeader()})
  },

  //评价面试过程
  evaluationInterview  (id, param) {
    return axios.post(api.evaluationInterview(id, param), JSON.stringify(param), {headers: this.getHeader()})
  },

  //操作云签约
  cloudSign(id, status){
    return axios.post(api.cloudSign(id, status), null, {headers: this.getHeader()})
  },

  //宣讲列表
  doqueryExplainMeeting (param, start) {
    console.log(start);
    if(start == null || typeof(start) == 'undefined'){
      start = 1;
    }

    return axios.post(api.doqueryExplainMeeting(), {draw:1, start: start, length: 10, condition: param}, {headers: this.getHeader()})
  },
  //宣讲详情
  viewExplainMeeting (id) {
    console.log(id);
    return axios.get(api.viewExplainMeeting(id), {headers: this.getHeader()})
  },
  //删除宣讲
  deleteExplainMeeting (id) {
    console.log(id);
    return axios.get(api.deleteExplainMeeting(id), {headers: this.getHeader()})
  },

  //保存宣讲
  saveExplainMeeting (explainMeeting) {
    console.log(explainMeeting);
    return axios.post(api.saveExplainMeeting(),JSON.stringify(explainMeeting), {headers: this.getHeader()})
  },

  //企业审核
  auditEnterprise(id, status){
    return axios.get(api.auditEnterprise(id, status), {headers: this.getHeader()})
  },

  getEnterpriseInfo(){
    return axios.get(api.getEnterpriseInfo(), {headers: this.getHeader()})
  }
}
