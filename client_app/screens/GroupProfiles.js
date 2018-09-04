import React, { Component } from 'react';
import { StyleSheet, View, Text, Button, TouchableOpacity,ScrollView } from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';
import ScreenDrawerBase from './main/ScreenDrawerBase';
import PersonPicture from '../components/PersonPicture';

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
    pictureRowContainer:{
        flexDirection:'row',
        padding:20
    },
    picture:{
        flex:1
    },
    pictureLast:{
        flex:1,
        marginLeft:10
    }
})
class GroupProfiles extends ScreenDrawerBase {

    state ={
        persons
    }

    showPersons=()=>{
        const { persons } = this.state;
        const results = [];
        for(let i = 0; i < persons.length; i+=2){
            results.push(
                <View style={styles.pictureRowContainer} key={i}>
                    <View style={styles.picture}>
                        <PersonPicture person = {persons[i]}/>
                    </View>
                    <View style={styles.pictureLast}>
                        {
                            (i+1 < persons.length) &&
                            <PersonPicture person = {persons[i+1]}/>
                        }
                    </View>
                </View>
            );
        }
        return results;
        // return persons.map((person, index)=>{
        //     return(
        //     <View key={index}>
        //         <PersonPicture person = {person}/>
        //     </View>
        // )})
    }
    render() {
        return (
            <ScrollView>
                <View style={{flexDirection:'row',alignItems:'center',justifyContent:'center', backgroundColor:'#d1d1d1', height:50, flex:1}}>
                    <View style={{flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center'}}>
                        <Text style={{fontWeight:'bold', color:'#000'}}>Aggregate Score :</Text>
                        <Text style={{fontWeight:'bold', color:'#3385ff', marginLeft:5}}>700</Text>
                    </View>
                    <View style={{flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center'}}>
                        <Text style={{fontWeight:'bold', color:'#000'}}>Credit Limit :</Text>
                        <Text style={{fontWeight:'bold', color:'#3385ff', marginLeft:5}}>500</Text>
                    </View>
                </View>
                <View style={{flexDirection:'column',alignItems:'center', backgroundColor:'#fff',paddingBottom:15}}>
                    {this.showPersons()}
                </View>
            </ScrollView>
        );
    }
}

export default GroupProfiles;