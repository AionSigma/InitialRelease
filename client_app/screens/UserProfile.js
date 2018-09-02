import React, { Component } from 'react';
import { View, Text, StyleSheet, ImageBackground,ScrollView, Button, TouchableOpacity  } from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';
import ScreenDrawerBase from './main/ScreenDrawerBase';
import { connect } from 'react-redux';
import ImageCircle from '../components/ImageCircle';
import images from './../assets';

const styles = StyleSheet.create({
  leftTextCol: {
    fontSize: 14,
    textAlign: 'left',
    margin: 3,
    color:'#000',
    fontWeight: 'bold'
  },
  rightTextCol: {
    fontSize: 14,
    textAlign: 'left',
    margin: 3,
    color:'#0061ff',
    fontWeight: 'bold'
  },
  descContainer:{
    flex:6,
    flexDirection:'column',
    backgroundColor:'#e6e8ed',
    marginTop:5,
    borderRadius:4
  },
  desc:{
    fontSize: 10,
    textAlign: 'center',
    margin: 3,
    color:'#4a81db',
    fontWeight: 'bold'
  }
});

function mapStateToProps(state) {
    return {

    };
}
class UserProfile extends ScreenDrawerBase {

    onPress=()=>{
        return ;
    }
    render() {
        return (
            <View style={{flex:1}}>
                <View style={{flex:1}}>
                    <ImageBackground
                        source={images.background1}
                        style={{width: '100%', height: '100%'}}
                    >
                        <View style={{flex:1,backgroundColor: 'rgba(0,0,0,0.4)', alignItems:'center',justifyContent:'center'}}>
                            <ImageCircle size={100} />
                            <Text style={{fontSize:20, fontWeight:'bold', color:'#fff', marginTop:10}}>Mary Odunlade</Text>
                            <Text style={{fontSize:14, fontWeight:'bold', color:'#fff'}}>Entrepreneur, Lagos</Text>
                        </View>
                    </ImageBackground>
                </View>
                <View style={{flex:1, marginTop:10}}>
                    <View style={{marginTop:-30, marginLeft:30}}>
                        <TouchableOpacity onPress={this.onPress} style={{width:40, height:40,borderRadius:20
                            ,alignItems:'center', justifyContent:'center'
                            ,backgroundColor:'#00e1ff'
                            ,shadowColor: 'black'
                            ,shadowOpacity: 0.6
                            ,shadowRadius: 1
                            ,shadowOffset: {width: 40,height: 40}
                            ,elevation: 2
                            }}>
                            <Icon name="md-add" size={30} color="#fff" />
                        </TouchableOpacity>
                    </View>
                    <ScrollView>
                        <View>
                            <View style={{flexDirection:'row'}}>
                                <View style={{flex:1}}/>
                                <View style={{flex:3}}>
                                    <Text style={styles.leftTextCol}>Credit Limit:</Text>
                                    <Text style={styles.leftTextCol}>Loans:</Text>
                                    <Text style={styles.leftTextCol}>Description:</Text>
                                    <Text style={styles.leftTextCol}>Backed by:</Text>
                                </View>
                                <View style={{flex:1}}/>
                                <View style={{flex:3}}>
                                    <Text style={styles.rightTextCol}>500</Text>
                                    <Text style={styles.rightTextCol}>3</Text>
                                    <Text style={styles.rightTextCol}>Trustworthy</Text>
                                    <Text style={styles.rightTextCol}>3 Persons</Text>
                                </View>
                            </View>
                        </View>
                        <View style={{flexDirection:'row'}}>
                            <View style={{flex:1}}/>
                            <View style={styles.descContainer}>
                                <Text style={styles.desc}>Odunlade from CC bank sent you request to check score</Text>
                                <Text style={styles.desc}>You will earn 5 credits for approving the request</Text>
                            </View>
                            <View style={{flex:1}}/>
                        </View>
                        <View style={{flexDirection:'row', marginTop:15, marginBottom:5}}>
                            <View style={{flex:1}}/>
                            <View style={{flex:1, alignItems:'center'}}>
                                <Button color="#00ff33" title="Review" onPress={this.onPress}/>
                            </View>
                            <View style={{flex:1, alignItems:'center'}}>
                                <Button color="red" title="Decline" onPress={this.onPress}/>
                            </View>
                            <View style={{flex:1}}/>
                        </View>
                    </ScrollView>
                </View>
                
            </View>
        );
    }
}

export default connect( mapStateToProps, null)(UserProfile);