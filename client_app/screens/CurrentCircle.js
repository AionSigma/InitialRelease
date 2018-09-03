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
    
    loanRaisedButton:{
        borderRadius:5
        ,alignItems:'center', justifyContent:'center'
        ,backgroundColor:'#1aff1a'
        ,shadowColor: 'black'
        ,shadowOpacity: 0.6
        ,shadowRadius: 1
        ,elevation: 2
        ,padding:5
    },
    loanBackedButton:{
        borderRadius:5
        ,alignItems:'center', justifyContent:'center'
        ,backgroundColor:'#ff3333'
        ,shadowColor: 'black'
        ,shadowOpacity: 0.6
        ,shadowRadius: 1
        ,elevation: 2
        ,padding:5
    }

})
class CurrentCircle extends ScreenDrawerBase {

    state ={
        persons
    }

    showPersons=()=>{
        const { persons } = this.state;
        return persons.map((person, index)=>{
            return(
            <View key={index}>
                <PersonLine person={person}>
                    {person.approved ?
                        <TouchableOpacity style={styles.loanRaisedButton} >
                            <Text style={{fontSize:14, fontWeight:'bold', color:'white'}}>Loan Raised</Text>
                        </TouchableOpacity>
                        :
                        <TouchableOpacity style={styles.loanBackedButton}>
                            <Text style={{fontSize:14, fontWeight:'bold', color:'white'}}>Loan Backed</Text>
                        </TouchableOpacity>
                    }
                </PersonLine>
            </View>
        )})
    }
    render() {
        return (
            <ScrollView>
                <View style={{justifyContent:'center', backgroundColor:'#e6f5ff',paddingBottom:15}}>
                    {this.showPersons()}
                </View>
            </ScrollView>
        );
    }
}

export default CurrentCircle;