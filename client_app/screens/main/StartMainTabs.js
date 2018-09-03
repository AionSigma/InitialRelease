import { Navigation } from 'react-native-navigation';
import Icon from 'react-native-vector-icons/Ionicons';

const leftButtons =(sources)=> [
    {
        icon: sources[0],
        title: "Menu",
        id: "sideDrawerToggle"
    }
];

const StartTabs = () => {
    Promise.all([
        Icon.getImageSource("md-menu", 20),
        Icon.getImageSource("md-home", 20),
        Icon.getImageSource("md-stats", 20),
        Icon.getImageSource("md-star", 20),
        Icon.getImageSource("md-person", 20),
        Icon.getImageSource("md-more", 20)

    ]).then(sources => {
        Navigation.startTabBasedApp({
            tabs: [
                {
                    screen: "aionSigma.UserProfile",
                    //label: "User Profile",
                    title: "User Profile",
                    icon: sources[1],
                    navigatorButtons: {
                        leftButtons:leftButtons(sources)
                    }
                },
                {
                    screen: "aionSigma.Friends",
                    //label: "Friends",
                    title: "Friends",
                    icon: sources[2],
                    navigatorButtons: {
                        leftButtons:leftButtons(sources)
                    }
                },
                {
                    screen: "aionSigma.CurrentCircle",
                    //label: "Current Circle",
                    title: "Current Circle",
                    icon: sources[3],
                    navigatorButtons: {
                        leftButtons:leftButtons(sources)
                    }
                },
                {
                    screen: "aionSigma.UserProfile",
                    //label: "User Profile",
                    title: "User Profile",
                    icon: sources[4],
                    navigatorButtons: {
                        leftButtons:leftButtons(sources)
                    }
                },
                {
                    screen: "aionSigma.Friends",
                    //label: "Friends",
                    title: "Friends",
                    icon: sources[5],
                    navigatorButtons: {
                        leftButtons:leftButtons(sources)
                    }
                }
            ],
            tabsStyle: { 
                initialTabIndex: 1,
            },
            appStyle: {
                navBarTextColor: '#fff', // change the text color of the title (remembered across pushes)
                navBarTextFontSize: 16, // change the font size of the title
                navBarBackgroundColor: '#527EEC', // change the background color of the nav bar (remembered across pushes)
                navBarComponentAlignment: 'center', // center/fill
                navBarButtonColor: '#fff',
                drawUnderNavBar: true,
                navBarTitleTextCentered: true, // default: false. centers the title.
                navBarSubTitleTextCentered: true, // (Android - default: false, iOS - default: depending on navBarTitleTextCentered). centers the subTitle.
                //navBarSubtitleFontSize: 12, // subtitle font size
                navBarHeight: 50,
                topTabsHeight: 50,
                tabBarSelectedButtonColor: '#1aa3ff', // change the color of the selected tab icon and text (only selected)
                // tabBarBackgroundColor: '#265BDE', // change the background color of the tab bar
                // tabBarTranslucent: false, // change the translucent of the tab bar to false
                 tabFontSize: 8,
                 selectedTabFontSize: 10,
                //tabBarTextFontFamily: 'Avenir-Medium', //change the tab font family
                // tabBarLabelColor: '#ffb700', // iOS only. change the color of tab text
                // tabBarSelectedLabelColor: 'red', // iOS only. change the color of the selected tab text
                forceTitlesDisplay: false, // Android only. If true - Show all bottom tab labels. If false - only the selected tab's label is visible.
                tabBarHideShadow: false, // Remove default tab bar top shadow (hairline)
                
              },
            drawer: {
                left: {
                    screen: "aionSigma.SideDrawer"
                }
             },
            // tabsStyle: { // optional, add this if you want to style the tab bar beyond the defaults
            //     tabBarButtonColor: '#ffff00', // optional, change the color of the tab icons and text (also unselected). On Android, add this to appStyle
            //     tabBarSelectedButtonColor: '#ff9900', // optional, change the color of the selected tab icon and text (only selected). On Android, add this to appStyle
            //     tabBarBackgroundColor: '#551A8B', // optional, change the background color of the tab bar
            //     initialTabIndex: 0, // optional, the default selected bottom tab. Default: 0. On Android, add this to appStyle
            // },
        });
    });
};

export default StartTabs;