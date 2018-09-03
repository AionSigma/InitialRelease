import React, { Component } from 'react';
import { StyleSheet, View, Text, Button, TouchableOpacity,ScrollView } from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';
import ScreenDrawerBase from './main/ScreenDrawerBase';
import PersonLine from '../components/PersonLine';

const persons = [
    {   id:1,
        name:"Name Here",
        phone:"XYZ",
        image:null,
        approved:true
    },
    {
        id:2,
        name:"Name Here",
        phone:"XYZ",
        image:null,
        approved:false
    },
    {
        id:3,
        name:"Name Here",
        phone:"XYZ",
        image:null,
        approved:false
    },
    {
        id:4,
        name:"Name Here",
        phone:"XYZ",
        image:null,
        approved:true
    },
    {
        id:5,
        name:"Name Here",
        phone:"XYZ",
        image:null,
        approved:false
    },
    {
        id:6,
        name:"Name Here",
        phone:"XYZ",
        image:null,
        approved:true
    },
    {
        id:7,
        name:"Name Here",
        phone:"XYZ",
        image:null,
        approved:false
    },
]

const styles = StyleSheet.create({
    checkButtonActive:{
        width:30, height:30,borderRadius:15
        ,alignItems:'center', justifyContent:'center'
        ,backgroundColor:'#1aff1a'
        ,shadowColor: 'black'
        ,shadowOpacity: 0.6
        ,shadowRadius: 1
        ,elevation: 2
    },
    checkButtonInactive:{
        width:30, height:30,borderRadius:15
        ,alignItems:'center', justifyContent:'center'
        ,backgroundColor:'#e6ffe6'
        ,shadowColor: 'black'
        ,shadowOpacity: 0.6
        ,shadowRadius: 1
        ,elevation: 2
    },
    closeButton:{
        width:30, height:30,borderRadius:15
        ,alignItems:'center', justifyContent:'center'
        ,backgroundColor:'#ff3333'
        ,shadowColor: 'black'
        ,shadowOpacity: 0.6
        ,shadowRadius: 1
        ,elevation: 2
        ,marginLeft:10
    }
})
class Friends extends ScreenDrawerBase {

    state ={
        persons
    }

    handleApproved=(id)=>{
        const {persons:arrPersons} = this.state;
        for(let i=0;i<arrPersons.length;i++){
            if(arrPersons[i].id===id){
                arrPersons[i].approved = !arrPersons[i].approved;
            }
        }
        this.setState({
            persons:arrPersons
        })
    }
    showPersons=(persons)=>{
        return persons.map((person, index)=>{
            const btnStype = person.approved? styles.checkButtonActive:styles.checkButtonInactive;
            return(
            <View key={index}>
                <PersonLine person={person}>
                    <TouchableOpacity style={btnStype} onPress={()=>this.handleApproved(person.id)}>
                        <Icon  name="md-checkmark" size={20} color="#fff" />
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.closeButton}>
                        <Icon name="md-close" size={20} color="#fff" />
                    </TouchableOpacity>
                </PersonLine>
            </View>
        )})
    }
    render() {
        return (
            <ScrollView>
                <View style={{justifyContent:'center', backgroundColor:'#e6f5ff',paddingBottom:15}}>
                    {this.showPersons(persons)}
                </View>
            </ScrollView>
        );
    }
}

export default Friends;