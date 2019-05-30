import React, { Component } from "react";
import { Carousel, CarouselItem } from "react-bootstrap";

const AnnouncementGallery = props => {
  return (
    <Carousel>
      <CarouselItem>
        <img className="carousel-image" src="https://thefederalist.com/wp-content/uploads/2017/10/overthegardenwall-998x690.jpg" />
        <div className="carousel-caption">
          <h3>First slide label</h3>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </div>
      </CarouselItem>
      <CarouselItem>
        <img className="carousel-image" src="https://thefederalist.com/wp-content/uploads/2017/10/overthegardenwall-998x690.jpg" />
        <div className="carousel-caption">
          <h3>Second slide label</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </div>
      </CarouselItem>
      <CarouselItem>
        <img className="carousel-image" src="https://thefederalist.com/wp-content/uploads/2017/10/overthegardenwall-998x690.jpg" />
        <div className="carousel-caption">
          <h3>Third slide label</h3>
          <p>
            Praesent commodo cursus magna, vel scelerisque nisl consectetur.
          </p>
        </div>
      </CarouselItem>
    </Carousel>
  );
};

export default AnnouncementGallery;
