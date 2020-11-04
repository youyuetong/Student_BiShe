// const host = 'http://pf.stalary.com'
const host = 'http://localhost:8999/job'

export default {
  getCompany () {
    return host + '/api/enterpriseInfo/doquery'
  },
  userRegister () {
    return host + '/user'
  },
  // 获取全部公司信息
  getComName () {
    return host + '/company/list'
  },
  userLogin () {
    return host + '/user/login'
  },
  getUserInfo () {
    return host + '/api/user/information'
  },
  sendCode () {
    return host + '/email/send'
  },
  logout () {
    return host + '/user/logout'
  },
  // 提交简历信息
  sendResume () {
    return host + '/resume'
  },
  // 获取投递列表
  deliveryList () {
    return host + '/api/jobRelation/listInvitation'
  },
  // 提交用户信息
  putUserInfo () {
    return host + '/api/user/update'
  },
  // 修改用户手机号码
  changePhone () {
    return host + '/user'
  },
  // 修改用户密码
  changePass () {
    return host + '/api/user/changePassword'
  },
  // 修改用户邮箱
  changeEmail () {
    return host + '/api/user/updateEmail'
  },
  // 修改微信ID
  changeWechatId () {
    return host + '/api/user/updateWechatId'
  },
  // 获取公司详情
  getCompanyDetail (id) {
    return host + `/api/enterpriseInfo/view/${id}`
  },
  // 获取简历信息
  getResume (userId) {
    return host + `/api/jobHunter/getJobHunterInfo/${userId}`
  },
  // 获取岗位详细信息
  getJobDetail (id) {
    return host + `/api/job/view2/${id}`
  },
  // 投递简历
  deliveryReusme (id) {
    return host + `/api/jobRelation/apply/${id}`
  },
  // 获取站内信
  getMessage () {
    return host + '/message/user/get'
  },
  // 搜索职位信息
  findJob () {
    return host + '/api/job/doquery'
  },
  // 已读
  readMessage () {
    return host + '/message/read'
  },
  // hr resumelist
  receiveResume () {
    return host + '/api/jobRelation/listJobHunter'
  },
  // hr发布职位信息
  saveJob () {
    return host + '/api/job/save'
  },

  // 职位发布
  publishJob (id) {
    return host + `/api/job/publish/${id}`
  },
  // 职位下线
  unpublishJob (id) {
    return host + `/api/job/unpublish/${id}`
  },
  // 查看hr发布的职位信息
  checkJob () {
    return host + '/api/job/getList'
  },
  deletejob (id) {
    return host + `/api/job/deleteJob/${id}`
  },
  hrRegister () {
    return host + '/user/register'
  },

  verifyMessage () {
    return host + '/email/verify'
  },
  // 推荐候选人
  recommendCandidate () {
    return host + '/recruit/recommend/candidate'
  },
  // 推荐职位
  recommendJob () {
    return host + '/recruit/recommend/job'
  },
  // 获取感兴趣的工作
  getRecommandJob () {
    return host + '/recruit/name'
  },
  
  invitation(id) {
    return host + `/api/jobRelation/invitation2/${id}`
  },
  uninvitation(id){
    return host + `/api/jobRelation/notSee/${id}`
  },
  receiveInterview(id, status){
    return host + `/api/jobRelation/acceptInterview/${id}/${status}`
  },
  passInterview(id, status){
    return host + `/api/jobRelation/updateInterviewResult/${id}/${status}`
  },
  evaluationInterview(id) {
    return host + `/api/jobRelation/evaluation/${id}`
  },
  cloudSign(id, status) {
    return host + `/api/jobRelation/cloudSign/${id}/${status}`
  },

  //宣讲
  doqueryExplainMeeting() {
    return host + '/api/explainMeeting/doquery'
  },
  viewExplainMeeting(id){
    return host + `/api/explainMeeting/view/${id}`
  },
  deleteExplainMeeting(id){
    return host + `/api/explainMeeting/delete/${id}`
  },
  saveExplainMeeting(){
    return host + '/api/explainMeeting/save'
  },

  auditEnterprise(id, status){
    return host + `/api/enterpriseInfo/audit/${id}/${status}`
  },

  getEnterpriseInfo(){
    return host + `/api/enterpriseInfo/getEnterpriseInfo`
  }
}
