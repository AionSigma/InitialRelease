import React, { Component } from 'react';
import { View, Text, Button } from 'react-native';
import ScreenDrawerBase from './main/ScreenDrawerBase';
import { connect } from 'react-redux';
import ImageCircle from '../components/ImageCircle';

function mapStateToProps(state) {
    return {

    };
}
class UserProfile extends ScreenDrawerBase {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <View>
                <ImageCircle></ImageCircle>
                <Text>UserProfile Screen</Text>
            </View>
        );
    }
}

export default connect( mapStateToProps, null)(UserProfile);