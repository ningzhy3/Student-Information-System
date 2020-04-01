import React, { Component } from 'react';
import Container from './Container';
import Footer from './Footer';
import './App.css';
import {getAllStudents} from './client';
import AddStudentForm from './forms/AddStudentForm';
import {
  Table, Avatar,Modal,Spin, Empty,
  
  
}from 'antd';
import {errorNotification} from './Notification';

import { LoadingOutlined } from '@ant-design/icons';

const getIndicatorIcon =()  => <LoadingOutlined style={{ fontSize: 24 }} spin />;

class App extends Component {

  state = {
    students:[],
    isFetching:false,
    isAddStudentModalVisisble: false
  }

  openAddStudentModal = () => this.setState({isAddStudentModalVisisble: true})

  closeAddStudentModal = () => this.setState({isAddStudentModalVisisble: false})

  componentDidMount () {
    this.fetchStudents();
  }
  
  fetchStudents = () =>{
    this.setState({
      isFetching: true
    })
    getAllStudents()
    .then(res => res.json()
    .then(students => {
      console.log(students);
    
    this.setState({
      students,
      isFetching: false
    });
    })).catch(error => {
      console.log(error.error);
      const message = error.error.message;
      const description = error.error.error;
      errorNotification(message,description);
      this.setState({isFetching:false})
    })
  };
  


  render () {
    const commonElements = () => (
      <div>
        <Modal title = 'Add new student'
                 visible={isAddStudentModalVisisble}
                 onOk={this.closeAddStudentModal}
                 onCancel={this.closeAddStudentModal}
                 width={1000}>
                  

                   <AddStudentForm
                   onSuccess={()=> {
                     this.closeAddStudentModal()
                     this.fetchStudents()
                     }}
                     
                   onFailure={(error)=>{
                      const message = error.error.message;
                      const description = error.error.httpstatus;
                       
                       errorNotification(message, description);

                     }}/>
                 
               </Modal>
               <Footer numberOfSutdents={students.length}
               handleAddStudentClickEvent={this.openAddStudentModal = () => {console.log('test') ;this.setState({isAddStudentModalVisisble:true})} }/>
      </div>
    )

    const {students,isFetching, isAddStudentModalVisisble} = this.state;

    if (isFetching) {
      return (
        <Container>
          <Spin indicator={getIndicatorIcon}/>

          
        </Container>
      );
    }

    if (students && students.length) {
      

      const columns = [

        {
          title :'',
          key:'avatar',

          render:(text,student) => (
            <Avatar size = 'large'>
              {`${student.firstName.charAt(0).toUpperCase()}${student.lastName.charAt(0).toUpperCase()}`}
              </Avatar>
          )

        },

        {
          title: 'StudentId',
          dataIndex: 'studentId',
          key:'studentId'
        },
        {
          title: 'firstname',
          dataIndex: 'firstName',
          key:'firstName'
        },
        {
          title: 'lastName',
          dataIndex: 'lastName',
          key:'lastName'
        },
        {
          title: 'Email',
          dataIndex: 'email',
          key:'email'
        },
        {
          title: 'gender',
          dataIndex: 'gender',
          key:'gender'
        },

    

      ];

      return (<Container>
                <Table
                style={{marginBottom: '100px'}}
                 dataSource = {students} 
                 columns = {columns} 
                 rowKey = 'studentId' />
                 {commonElements()}
              </Container>
              
      );
    
    }
    return (
      <Container>
    <Empty description={
      <h1>No students found</h1>
      

    }/>
    {commonElements()}
    </Container>
    )
  }

}

export default App;
