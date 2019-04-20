import React, { Component } from 'react';
import '../css/general-announcement.css'

class GeneralAnnouncement extends Component {
    render() {
        return (
            <div className="frame">
                <div className="image-container">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Temp_plate_blue.svg/601px-Temp_plate_blue.svg.png" alt=""/>
                </div>
                <div className="info-container">
                    <div className="details">
                        <div className="title">Title</div>
                        <div className="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rhoncus, erat ac dictum bibendum.</div>
                        <div className="bottom">
                            <div className="localization">Stare Miasto, Kraków</div>
                            <div className="creation-date">dzisiaj 16:00</div>
                        </div>
                    </div>
                    <div className="pricing">
                        <div className="price">2200 zł</div>
                        <div>Gumtree</div>
                        <div>Właściciel</div>
                    </div>
                </div>
            </div>
        );
    }
}

export default GeneralAnnouncement;