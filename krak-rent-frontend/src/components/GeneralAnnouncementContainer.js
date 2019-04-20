import React, { Component } from 'react';
import '../css/main.css'

import GeneralAnnouncement from "../components/GeneralAnnouncement";


class GeneralAnnouncementContainer extends Component {
    render() {
        const generalAnnouncements = this.props.data.map(an => <GeneralAnnouncement key={an.id} item={an} />)
        return (
            <div className="announcements-container">
                {generalAnnouncements}
            </div>
        )
    }
}

export default GeneralAnnouncementContainer;