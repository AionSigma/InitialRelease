import React, { Component } from 'react';
import { StyleSheet, View, Text, Image  } from 'react-native';
import PropTypes from 'prop-types';
import Icon from 'react-native-vector-icons/Ionicons';
import images from './../assets'

const styles = StyleSheet.create({
    container:{
        flexDirection:'column',
        alignItems:'center',
        justifyContent:'center'
    },
    icon:{
        marginLeft:4
    },
    image:{
        width: 140,
        height: 140,
        borderRadius:5
    }
})

class PersonPicture extends Component {
    render() {
        const {person} = this.props;
        return (
            <View style={styles.container}>
                <View>
                    <Image style={styles.image} source={images.avatar_playholder} />
                </View>
                <Text style={{fontWeight:'bold', margin:10}}>{person.name}</Text>
                <View style={{flexDirection:'row'}}>
                    <View>
                        <Icon name="md-star" size={18} color="#33adff" />
                    </View>
                    <View style={styles.icon}>
                        <Icon name="md-star" size={18} color="#33adff" />
                    </View>
                    <View style={styles.icon}>
                        <Icon name="md-star" size={18} color="#33adff" />
                    </View>
                    <View style={styles.icon}>
                        <Icon name="md-star" size={18} color="#33adff" />
                    </View>
                    <View style={styles.icon}>
                        <Icon name="md-star" size={18} color="#33adff" />
                    </View>
                </View>
            </View>
        );
    }
}

PersonPicture.propTypes = {
    person: PropTypes.shape({
        name: PropTypes.string.isRequired,
        phone: PropTypes.string.isRequired,
    }).isRequired,
};

export default PersonPicture;