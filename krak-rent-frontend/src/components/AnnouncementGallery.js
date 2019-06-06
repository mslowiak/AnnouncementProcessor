import React from "react";
import { Carousel, CarouselItem } from "react-bootstrap";
import "../css/carousel-gallery.css";

const AnnouncementGallery = props => {
  const carouselItems =
    props.images.length > 0
      ? props.images.map(imgUrl => {
          return (
            <CarouselItem>
              <img className="carousel-image" src={imgUrl} />
            </CarouselItem>
          );
        })
      : [
          <CarouselItem>
            <img
              className="carousel-image"
              src="https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg"
            />
          </CarouselItem>
        ];

  return <Carousel>{carouselItems}</Carousel>;
};

export default AnnouncementGallery;
