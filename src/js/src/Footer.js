import React from 'react';
import Container from './Container';
import { Button, Avatar } from 'antd';
import './Footer.css';

const Footer = (props) => (
    <div className='footer'>
        <Container>
            {props.numberOfStudents !== undefined ? 
            <Avatar style={{backgroundColor: 'f56a00', marginRiight: '5px'}}
            size='large'>{props.numberOfStudents}</Avatar> : null}
            <Button onClick= {() => props.handleAddStudentClickEvent() }type='primary'>Add new+++ </Button>           
        </Container>
    </div>

);

export default Footer;

