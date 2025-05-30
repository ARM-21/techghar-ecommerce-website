<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - TechGhar</title>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* Reset and Base Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Poppins', sans-serif;
            color: #4a5568;
            background-color: #f7fafc;
            line-height: 1.5;
            overflow-x: hidden;
        }
        
        .container {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 15px;
        }
        
        a {
            text-decoration: none;
            color: inherit;
        }
        

      
        
        /* Hero Banner */
        .hero-banner {
            background: linear-gradient(to right, #0a2540, #1e3a8a);
            color: white;
            padding: 80px 0;
            margin-bottom: 60px;
            position: relative;
            overflow: hidden;
        }
        
        .hero-banner::before {
            content: '';
            position: absolute;
            top: -50px;
            right: -50px;
            width: 200px;
            height: 200px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.1);
            z-index: 1;
        }
        
        .hero-banner::after {
            content: '';
            position: absolute;
            bottom: -80px;
            left: -80px;
            width: 300px;
            height: 300px;
            border-radius: 50%;
            background: rgba(0, 212, 255, 0.05);
            z-index: 1;
        }
        
        .hero-title {
            font-weight: 700;
            font-size: 2.5rem;
            margin-bottom: 15px;
            position: relative;
            z-index: 2;
        }
        
        .hero-subtitle {
            font-size: 1.1rem;
            margin-bottom: 0;
            opacity: 0.9;
            max-width: 700px;
            margin: 0 auto;
            position: relative;
            z-index: 2;
        }
        
        /* Contact Section */
        .contact-section {
            padding-bottom: 60px;
        }
        
        .section-title {
            text-align: center;
            margin-bottom: 40px;
            position: relative;
        }
        
        .section-title h2 {
            font-weight: 700;
            font-size: 2.2rem;
            display: inline-block;
            position: relative;
            z-index: 1;
            margin-bottom: 15px;
            color: #1a202c;
        }
        
        .section-title h2::after {
            content: '';
            position: absolute;
            width: 60px;
            height: 4px;
            background-color: #635bff;
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            border-radius: 2px;
        }
        
        .section-title p {
            max-width: 700px;
            margin: 0 auto;
            color: #4a5568;
        }
        
        .contact-row {
            display: flex;
            flex-wrap: wrap;
            margin: 0 -15px;
        }
        
        .contact-col-4 {
            flex: 0 0 33.333333%;
            max-width: 33.333333%;
            padding: 0 15px;
        }
        
        .contact-col-8 {
            flex: 0 0 66.666667%;
            max-width: 66.666667%;
            padding: 0 15px;
        }
        
        .contact-info-card {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
            padding: 30px;
            margin-bottom: 30px;
            height: 100%;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        
        .contact-info-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px -5px rgba(0, 0, 0, 0.1);
        }
        
        .contact-info-card h3 {
            font-weight: 600;
            color: #1a202c;
            margin-bottom: 20px;
            font-size: 1.5rem;
        }
        
        .contact-info-item {
            display: flex;
            align-items: flex-start;
            margin-bottom: 20px;
        }
        
        .contact-info-item:last-child {
            margin-bottom: 0;
        }
        
        .contact-icon {
            width: 50px;
            height: 50px;
            background-color: #f7fafc;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 15px;
            color: #635bff;
            font-size: 1.2rem;
            flex-shrink: 0;
            transition: all 0.3s ease;
        }
        
        .contact-info-item:hover .contact-icon {
            background-color: #635bff;
            color: white;
            transform: scale(1.1);
        }
        
        .contact-text {
            flex: 1;
        }
        
        .contact-text h4 {
            font-weight: 600;
            color: #1a202c;
            margin-bottom: 5px;
            font-size: 1.1rem;
        }
        
        .contact-text p {
            margin-bottom: 0;
            line-height: 1.6;
        }
        
        .contact-form-card {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
            padding: 30px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        
        .contact-form-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px -5px rgba(0, 0, 0, 0.1);
        }
        
        .form-row {
            display: flex;
            flex-wrap: wrap;
            margin: 0 -10px;
        }
        
        .form-col {
            flex: 1;
            padding: 0 10px;
            min-width: 200px;
        }
        
        .form-group {
            margin-bottom: 25px;
        }
        
        .form-label {
            font-weight: 500;
            margin-bottom: 8px;
            display: block;
            color: #1a202c;
        }
        
        .form-control {
            width: 100%;
            height: 50px;
            border-radius: 8px;
            border: 1px solid #e2e8f0;
            padding: 10px 15px;
            font-size: 1rem;
            transition: all 0.3s ease;
        }
        
        .form-control:focus {
            border-color: #635bff;
            box-shadow: 0 0 0 3px rgba(99, 91, 255, 0.2);
            outline: none;
        }
        
        textarea.form-control {
            height: 150px;
            resize: none;
        }
        
        .submit-btn {
            display: inline-block;
            padding: 12px 30px;
            background-color: #635bff;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(99, 91, 255, 0.2);
            position: relative;
            overflow: hidden;
        }
        
        .submit-btn:hover {
            background-color: #5851e6;
            transform: translateY(-2px);
            box-shadow: 0 6px 8px rgba(99, 91, 255, 0.3);
        }
        
        /* Button ripple effect */
        .submit-btn .ripple {
            position: absolute;
            border-radius: 50%;
            background-color: rgba(255, 255, 255, 0.4);
            transform: scale(0);
            animation: ripple 0.6s linear;
        }
        
        @keyframes ripple {
            to {
                transform: scale(2.5);
                opacity: 0;
            }
        }
        
        /* Thank You Message */
        .thank-you-message {
            display: none;
            background-color: #e6f7ef;
            border-left: 4px solid #10b981;
            padding: 15px;
            margin-top: 20px;
            border-radius: 8px;
            animation: slideDown 0.4s ease;
        }
        
        @keyframes slideDown {
            from { transform: translateY(-20px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }
        
        .thank-you-message p {
            color: #065f46;
            margin-bottom: 0;
            font-weight: 500;
        }
        
        /* Map Section */
        .map-section {
            padding: 60px 0;
            background-color: white;
        }
        
        .map-container {
            height: 450px;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
            position: relative;
        }
        
        .map-container iframe {
            width: 100%;
            height: 100%;
            border: none;
        }
        
        .map-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(10, 37, 64, 0.05);
            pointer-events: none;
            opacity: 0;
            transition: opacity 0.3s ease;
        }
        
        .map-container:hover .map-overlay {
            opacity: 1;
        }
        
        /* FAQ Section */
        .faq-section {
            padding: 60px 0;
        }
        
        .accordion {
            margin-bottom: 30px;
        }
        
        .accordion-item {
            margin-bottom: 15px;
            border: none;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
            background-color: white;
            transition: box-shadow 0.3s ease;
        }
        
        .accordion-item:hover {
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        
        .accordion-header {
            margin: 0;
        }
        
        .accordion-button {
            padding: 20px;
            font-weight: 600;
            color: #1a202c;
            background-color: white;
            border: none;
            width: 100%;
            text-align: left;
            cursor: pointer;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: space-between;
            transition: all 0.3s ease;
        }
        
        .accordion-button:after {
            content: '\f107';
            font-family: 'Font Awesome 5 Free';
            font-weight: 900;
            color: #1a202c;
            transition: all 0.3s ease;
        }
        
        .accordion-button.active {
            color: #635bff;
        }
        
        .accordion-button.active:after {
            transform: rotate(180deg);
            color: #635bff;
        }
        
        .accordion-content {
            max-height: 0;
            overflow: hidden;
            transition: max-height 0.3s ease;
        }
        
        .accordion-body {
            padding: 0 20px 20px;
            line-height: 1.8;
        }
        
      
   
        
        /* Popup Message */
        .popup-message {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%) scale(0.9);
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            z-index: 1100;
            max-width: 400px;
            width: 90%;
            text-align: center;
            opacity: 0;
            visibility: hidden;
            transition: all 0.3s ease;
        }
        
        .popup-message.show {
            opacity: 1;
            visibility: visible;
            transform: translate(-50%, -50%) scale(1);
        }
        
        .popup-icon {
            width: 70px;
            height: 70px;
            background-color: #f0fdf4;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px;
            color: #10b981;
            font-size: 2rem;
        }
        
        .popup-title {
            font-size: 1.5rem;
            font-weight: 700;
            color: #1a202c;
            margin-bottom: 10px;
        }
        
        .popup-text {
            color: #4a5568;
            margin-bottom: 20px;
        }
        
        .popup-btn {
            display: inline-block;
            padding: 10px 25px;
            background-color: #635bff;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        
        .popup-btn:hover {
            background-color: #5851e6;
            transform: translateY(-2px);
        }
        
        .popup-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1050;
            opacity: 0;
            visibility: hidden;
            transition: all 0.3s ease;
        }
        
        .popup-overlay.show {
            opacity: 1;
            visibility: visible;
        }
        
        /* Social Media Icons */
        .social-icons {
            display: flex;
            gap: 15px;
            margin-top: 20px;
        }
        
        .social-icon {
            width: 40px;
            height: 40px;
            background-color: #f7fafc;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #1a202c;
            font-size: 1.2rem;
            transition: all 0.3s ease;
        }
        
        .social-icon:hover {
            background-color: #635bff;
            color: white;
            transform: translateY(-3px);
        }
        
        /* Responsive Adjustments */
        @media (max-width: 992px) {
            .contact-col-4, .contact-col-8 {
                flex: 0 0 100%;
                max-width: 100%;
            }
            
            .hero-title {
                font-size: 2rem;
            }
            
            .hero-subtitle {
                font-size: 1rem;
            }
            
            .section-title h2 {
                font-size: 1.8rem;
            }
        }
        
        @media (max-width: 768px) {
            .navbar-toggler {
                display: block;
            }
            
            .navbar-collapse {
                display: none;
                width: 100%;
                flex-direction: column;
                position: absolute;
                top: 70px;
                left: 0;
                background-color: white;
                padding: 20px;
                box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
            }
            
            .navbar-collapse.show {
                display: flex;
            }
            
            .navbar-nav {
                flex-direction: column;
                width: 100%;
                margin-bottom: 15px;
            }
            
            .nav-item {
                margin: 8px 0;
            }
            
            .nav-icons {
                width: 100%;
                justify-content: flex-start;
            }
            
            .nav-icons a {
                margin-left: 0;
                margin-right: 20px;
            }
            
            .hero-banner {
                padding: 60px 0;
            }
            
            .hero-title {
                font-size: 1.8rem;
            }
            
            .map-container {
                height: 350px;
            }
            
            .form-row {
                flex-direction: column;
            }
            
            .form-col {
                width: 100%;
                margin-bottom: 0;
            }
        }
        
        @media (max-width: 576px) {
            .hero-banner {
                padding: 40px 0;
            }
            
            .hero-title {
                font-size: 1.5rem;
            }
            
            .section-title h2 {
                font-size: 1.5rem;
            }
            
            .contact-info-card, .contact-form-card {
                padding: 20px;
            }
            
            .map-container {
                height: 300px;
            }
            
            .popup-message {
                padding: 20px;
            }
            
            .popup-icon {
                width: 60px;
                height: 60px;
                font-size: 1.5rem;
            }
            
            .popup-title {
                font-size: 1.3rem;
            }
        }
    </style>
</head>
<body>

    <!-- Hero Banner -->
    <section class="hero-banner">
        <div class="container text-center">
            <h1 class="hero-title">Contact Us</h1>
            <p class="hero-subtitle">We'd love to hear from you. Reach out to us with any questions, feedback, or support needs.</p>
        </div>
    </section>

    <!-- Contact Section -->
    <section class="contact-section">
        <div class="container">
            <div class="section-title">
                <h2>Get In Touch</h2>
                <p>Our team is here to help you with any inquiries</p>
            </div>
            
            <div class="contact-row">
                <div class="contact-col-4">
                    <div class="contact-info-card">
                        <h3>Contact Information</h3>
                        
                        <div class="contact-info-item">
                            <div class="contact-icon">
                                <i class="fas fa-map-marker-alt"></i>
                            </div>
                            <div class="contact-text">
                                <h4>Our Location</h4>
                                <p>123 Tech Street, Lakeside, Pokhara, Nepal</p>
                            </div>
                        </div>
                        
                        <div class="contact-info-item">
                            <div class="contact-icon">
                                <i class="fas fa-phone-alt"></i>
                            </div>
                            <div class="contact-text">
                                <h4>Phone Number</h4>
                                <p>+977 1234567890</p>
                                <p>+977 9876543210</p>
                            </div>
                        </div>
                        
                        <div class="contact-info-item">
                            <div class="contact-icon">
                                <i class="fas fa-envelope"></i>
                            </div>
                            <div class="contact-text">
                                <h4>Email Address</h4>
                                <p>techghar.online@gmail.com</p>
                                <p>support@techghar.com</p>
                            </div>
                        </div>
                        
                        <div class="contact-info-item">
                            <div class="contact-icon">
                                <i class="fas fa-clock"></i>
                            </div>
                            <div class="contact-text">
                                <h4>Working Hours</h4>
                                <p>Monday - Friday: 9:00 AM - 7:00 PM</p>
                                <p>Saturday: 10:00 AM - 5:00 PM</p>
                                <p>Sunday: Closed</p>
                            </div>
                        </div>
                        
                        <div class="social-icons">
                            <a href="#" class="social-icon" title="Facebook">
                                <i class="fab fa-facebook-f"></i>
                            </a>
                            <a href="#" class="social-icon" title="Twitter">
                                <i class="fab fa-twitter"></i>
                            </a>
                            <a href="#" class="social-icon" title="Instagram">
                                <i class="fab fa-instagram"></i>
                            </a>
                            <a href="#" class="social-icon" title="LinkedIn">
                                <i class="fab fa-linkedin-in"></i>
                            </a>
                        </div>
                    </div>
                </div>
                
                <div class="contact-col-8">
                    <div class="contact-form-card">
                        <form id="contactForm" action="https://api.web3forms.com/submit" method="POST">
                            <!-- Web3Forms API Key -->
                            <input type="hidden" name="access_key" value="d0ab3666-436d-4203-a9ae-0f7ac62b038c">
                            
                            <div class="form-row">
                                <div class="form-col">
                                    <div class="form-group">
                                        <label for="name" class="form-label">Your Name</label>
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name" required>
                                    </div>
                                </div>
                                <div class="form-col">
                                    <div class="form-group">
                                        <label for="email" class="form-label">Email Address</label>
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class="form-col">
                                    <div class="form-group">
                                        <label for="phone" class="form-label">Phone Number</label>
                                        <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter your phone number">
                                    </div>
                                </div>
                                <div class="form-col">
                                    <div class="form-group">
                                        <label for="subject" class="form-label">Subject</label>
                                        <input type="text" class="form-control" id="subject" name="subject" placeholder="Enter subject" required>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="message" class="form-label">Your Message</label>
                                <textarea class="form-control" id="message" name="message" placeholder="Enter your message" required></textarea>
                            </div>
                            
                          
                            <input type="checkbox" name="botcheck" style="display: none;">
                            
                            <button type="submit" class="submit-btn" id="submitBtn">Send Message</button>
                        </form>
                        
                        <!-- Thank You Message (initially hidden) -->
                        <div class="thank-you-message" id="thankYouMessage">
                            <p><i class="fas fa-check-circle me-2"></i> Thank you for your message! We'll get back to you as soon as possible.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Map Section -->
    <section class="map-section">
        <div class="container">
            <div class="map-container">
                
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14001.635310566732!2d83.95233687325653!3d28.20760238770089!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3995951e9cb6e75d%3A0x5ef1e13ad2face48!2sLakeside%2C%20Pokhara%2033700!5e0!3m2!1sen!2snp!4v1649089900203!5m2!1sen!2snp" allowfullscreen="" loading="lazy"></iframe>
                <div class="map-overlay"></div>
            </div>
        </div>
    </section>

    <!-- FAQ Section -->
    <section class="faq-section">
        <div class="container">
            <div class="section-title">
                <h2>Frequently Asked Questions</h2>
                <p>Find answers to common questions about our services</p>
            </div>
            
            <div class="accordion" id="faqAccordion">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-target="collapseOne">
                            What payment methods do you accept?
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-content">
                        <div class="accordion-body">
                            We accept various payment methods including credit/debit cards, eSewa, Khalti, bank transfers, and cash on delivery for orders within Pokhara. All online payments are processed through secure payment gateways to ensure your financial information remains safe.
                        </div>
                    </div>
                </div>
                
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-target="collapseTwo">
                            What is your shipping policy?
                        </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-content">
                        <div class="accordion-body">
                            We offer free shipping on orders above NPR 10,000. For orders below this amount, shipping fees vary based on location. Delivery within Pokhara typically takes 1-2 business days, while nationwide delivery takes 3-5 business days. International shipping is available for select countries with delivery times ranging from 7-14 business days.
                        </div>
                    </div>
                </div>
                
                    <div id="collapseThree" class="accordion-content">
                        <div class="accordion-body">
                            We offer a 7-day return policy for most products, provided they are in their original condition with all packaging and accessories. Some items like software, opened consumables, and custom-configured systems may not be eligible for return unless defective. Refunds are typically processed within 5-7 business days after we receive and inspect the returned item.
                        </div>
                    </div>
                </div>
                
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-target="collapseFour">
                            Do you offer warranty on your products?
                        </button>
                    </h2>
                    <div id="collapseFour" class="accordion-content">
                        <div class="accordion-body">
                            Yes, all our products come with the manufacturer's warranty. Most laptops and desktops include a 1-2 year warranty, while accessories typically have a 6-month to 1-year warranty. We also offer extended warranty options for select products. Our technical team provides support for warranty claims and repairs to make the process hassle-free for our customers.
                        </div>
                    </div>
                </div>
                
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-target="collapseFive">
                            Do you offer technical support after purchase?
                        </button>
                    </h2>
                    <div id="collapseFive" class="accordion-content">
                        <div class="accordion-body">
                            We provide technical support for all products purchased from TechGhar. Our support team is available via phone, email, and in-store visits during business hours. For complex issues, we offer repair and maintenance services at our service center. We also provide free software installation and basic setup assistance with every purchase.
                        </div>
                    </div>
                </div>
                
                
        </div>
    </section>
    
    <!-- Popup Thank You Message -->
    <div class="popup-overlay" id="popupOverlay"></div>
    <div class="popup-message" id="popupMessage">
        <div class="popup-icon">
            <i class="fas fa-check-circle"></i>
        </div>
        <h3 class="popup-title">Thank You!</h3>
        <p class="popup-text">Your message has been sent successfully. Our team will get back to you as soon as possible.</p>
        <button class="popup-btn" id="popupCloseBtn">Close</button>
    </div>

    <!-- Custom JS -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Mobile Navigation Toggle
            const navbarToggler = document.getElementById('navbarToggler');
            const navbarNav = document.getElementById('navbarNav');
            
            navbarToggler.addEventListener('click', function() {
                navbarNav.classList.toggle('show');
            });
            
            // Accordion functionality
            const accordionButtons = document.querySelectorAll('.accordion-button');
            
            accordionButtons.forEach(button => {
                button.addEventListener('click', function() {
                    const targetId = this.getAttribute('data-target');
                    const targetContent = document.getElementById(targetId);
                    
                    // Toggle active class on button
                    this.classList.toggle('active');
                    
                    // Toggle content visibility
                    if (targetContent.style.maxHeight) {
                        targetContent.style.maxHeight = null;
                    } else {
                        targetContent.style.maxHeight = targetContent.scrollHeight + "px";
                    }
                });
            });
            
            // Open first accordion by default
            document.querySelector('.accordion-button').click();
            
            // Form submission
            const form = document.getElementById('contactForm');
            const submitBtn = document.getElementById('submitBtn');
            const thankYouMessage = document.getElementById('thankYouMessage');
            const popupMessage = document.getElementById('popupMessage');
            const popupOverlay = document.getElementById('popupOverlay');
            const popupCloseBtn = document.getElementById('popupCloseBtn');
            
            form.addEventListener('submit', function(e) {
                e.preventDefault();
                
                // Add loading state to button
                submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Sending...';
                submitBtn.disabled = true;
                
                // Create form data
                const formData = new FormData(form);
                
                // Simulate form submission (replace with actual submission in production)
                setTimeout(function() {
                    // Show popup thank you message
                    popupMessage.classList.add('show');
                    popupOverlay.classList.add('show');
                    
                    // Also show inline thank you message
                    thankYouMessage.style.display = 'block';
                    
                    // Reset form
                    form.reset();
                    
                    // Reset button state
                    submitBtn.innerHTML = 'Send Message';
                    submitBtn.disabled = false;
                }, 1500);
                
                
               
                fetch('https://api.web3forms.com/submit', {
                    method: 'POST',
                    body: formData
                })
                .then(response => response.json())
                .then(data => {
                    // Show popup thank you message
                    popupMessage.classList.add('show');
                    popupOverlay.classList.add('show');
                    
                    // Also show inline thank you message
                    thankYouMessage.style.display = 'block';
                    
                    // Reset form
                    form.reset();
                    
                    // Reset button state
                    submitBtn.innerHTML = 'Send Message';
                    submitBtn.disabled = false;
                })
                .catch(error => {
                    console.error('Error:', error);
                    submitBtn.innerHTML = 'Send Message';
                    submitBtn.disabled = false;
                    alert('There was an error sending your message. Please try again.');
                });
                
            });
            
            // Close popup
            popupCloseBtn.addEventListener('click', function() {
                popupMessage.classList.remove('show');
                popupOverlay.classList.remove('show');
            });
            
            popupOverlay.addEventListener('click', function() {
                popupMessage.classList.remove('show');
                popupOverlay.classList.remove('show');
            });
            
 
        });
    </script>
</body>
</html>