<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mobile Brand Carousel</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carousel.css" />
</head>
<body>
    <div class="carousel">
        <div class="slides">
            <c:forEach var="item" items="${carouselItems}" varStatus="loop">
                <div class="slide ${loop.index == 0 ? 'active' : ''}"
                    style="background-image: url('${item.imageUrl}');">
                    <div class="slide-content">
                        <h2>${item.title}</h2>
                        <p>${item.description}</p>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="carousel-controls">
            <button class="prev" onclick="prevSlide()">&#10094;</button>
            <button class="next" onclick="nextSlide()">&#10095;</button>
        </div>

        <div class="dots">
            <c:forEach var="item" items="${carouselItems}" varStatus="loop">
                <div class="dot ${loop.index == 0 ? 'active' : ''}" onclick="showSlide(${loop.index})"></div>
            </c:forEach>
        </div>
    </div>

    <script>
        let currentSlide = 0;
        const slides = document.querySelectorAll('.slide');
        const dots = document.querySelectorAll('.dot');
        const totalSlides = slides.length;
        let slideInterval;

        function showSlide(n) {
            // Handle slide boundaries
            if (n >= totalSlides) n = 0;
            if (n < 0) n = totalSlides - 1;
            
            // Hide all slides
            slides.forEach(slide => slide.classList.remove('active'));
            // Show current slide
            slides[n].classList.add('active');
            
            // Update dots
            dots.forEach((dot, index) => {
                dot.classList.toggle('active', index === n);
            });
            
            currentSlide = n;
        }

        function prevSlide() {
            showSlide(currentSlide - 1);
            resetInterval();
        }

        function nextSlide() {
            showSlide(currentSlide + 1);
            resetInterval();
        }

        function resetInterval() {
            clearInterval(slideInterval);
            slideInterval = setInterval(nextSlide, 5000);
        }

        // Initialize autoplay
        slideInterval = setInterval(nextSlide, 5000);

        // Add touch/swipe support
        let touchStartX = 0;
        let touchEndX = 0;
        const swipeThreshold = 50;

        document.querySelector('.carousel').addEventListener('touchstart', e => {
            touchStartX = e.changedTouches[0].screenX;
        });

        document.querySelector('.carousel').addEventListener('touchend', e => {
            touchEndX = e.changedTouches[0].screenX;
            handleSwipe();
        });

        function handleSwipe() {
            const delta = touchStartX - touchEndX;
            if (delta > swipeThreshold) nextSlide();
            if (delta < -swipeThreshold) prevSlide();
        }

        // Pause on hover
        document.querySelector('.carousel').addEventListener('mouseenter', () => {
            clearInterval(slideInterval);
        });

        document.querySelector('.carousel').addEventListener('mouseleave', () => {
            slideInterval = setInterval(nextSlide, 5000);
        });
    </script>
</body>
</html>