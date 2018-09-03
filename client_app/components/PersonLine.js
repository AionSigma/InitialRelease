import React, { Component } from 'react';
import { StyleSheet, View, Text  } from 'react-native';
import PropTypes from 'prop-types';
import ImageCircle from './ImageCircle';

const styles = StyleSheet.create({
    container:{
        flexDirection:'row',
        marginTop:15,
        marginRight:15,
        marginBottom:0,
        marginLeft:15,
        padding:15,
        backgroundColor:'#fff',
        borderRadius:10,
        
        
    },
    containerLeft:{
        flex:1,
        flexDirection:'row',
    },
    containerRight:{
        flex:1,
        flexDirection:'row',
        alignItems:'center',
        justifyContent:'flex-end',
        margin:10
    }
})

class PersonLine extends Component {
    render() {
        const {person} = this.props;
        return (
            <View style={styles.container}>
                <View style={styles.containerLeft}>
                    <View>
                        <ImageCircle size={60} />
                    </View>
                    <View style={{margin:10}}>
                        <Text>{person.name}</Text>
                        <Text>{person.phone}</Text>
                    </View>
                </View>
                <View style={styles.containerRight}>
                    {this.props.children}
                </View>
            </View>
        );
    }
}

PersonLine.propTypes = {
    children: PropTypes.oneOfType([
        PropTypes.arrayOf(PropTypes.node),
        PropTypes.node
      ]),
    person: PropTypes.shape({
        name: PropTypes.string.isRequired,
        phone: PropTypes.string.isRequired,
    }).isRequired,
};

export default PersonLine;