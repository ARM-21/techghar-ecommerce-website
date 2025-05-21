<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - TechGhar</title>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        /* CSS Variables */
        :root {
            --primary-color: #0a2540;
            --secondary-color: #00d4ff;
            --accent-color: #635bff;
            --light-color: #f7fafc;
            --dark-color: #1a202c;
            --text-color: #4a5568;
            --border-radius: 8px;
            --box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
        }
   
        
        ul {
            list-style: none;
        }
        
        img {
            max-width: 100%;
            height: auto;
        }
        
        /* Layout */
        .container {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 15px;
        }
        
        .row {
            display: flex;
            flex-wrap: wrap;
            margin: 0 -15px;
        }
        
        .col-lg-3, .col-lg-6, .col-md-6 {
            padding: 0 15px;
            margin-bottom: 30px;
        }
        
        .col-lg-3 {
            width: 25%;
        }
        
        .col-lg-6 {
            width: 50%;
        }
        
        .align-items-center {
            align-items: center;
        }
        
        .text-center {
            text-align: center;
        }
        
      
        /* Navigation */
       
        .login-btn {
            background-color: var(--accent-color);
            color: white;
            padding: 8px 20px;
            border-radius: var(--border-radius);
            font-size: 0.9rem;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(99, 91, 255, 0.2);
        }
        
        .login-btn:hover {
            background-color: #5851e6;
            transform: translateY(-2px);
            box-shadow: 0 6px 8px rgba(99, 91, 255, 0.3);
        }
        
        /* Hero Banner */
        .hero-banner {
            background: linear-gradient(to right, var(--primary-color), #1e3a8a);
            color: white;
            padding: 80px 0;
            margin-bottom: 60px;
        }
        
        .hero-title {
            font-weight: 700;
            font-size: 2.5rem;
            margin-bottom: 15px;
        }
        
        .hero-subtitle {
            font-size: 1.1rem;
            margin-bottom: 0;
            opacity: 0.9;
            max-width: 700px;
            margin: 0 auto;
        }
        
        /* About Section */
        .about-section {
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
            color: var(--dark-color);
        }
        
        .section-title h2::after {
            content: '';
            position: absolute;
            width: 60px;
            height: 4px;
            background-color: var(--accent-color);
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            border-radius: 2px;
        }
        
        .section-title p {
            max-width: 700px;
            margin: 0 auto;
            color: var(--text-color);
        }
        
        .about-card {
            background-color: white;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            padding: 30px;
            margin-bottom: 30px;
            height: 100%;
        }
        
        .about-card h3 {
            font-weight: 600;
            color: var(--dark-color);
            margin-bottom: 20px;
            font-size: 1.5rem;
        }
        
        .about-card p {
            line-height: 1.8;
            margin-bottom: 20px;
        }
        
        .about-card p:last-child {
            margin-bottom: 0;
        }
        
        .about-image {
            border-radius: var(--border-radius);
            overflow: hidden;
            box-shadow: var(--box-shadow);
            height: 100%;
        }
        
        .about-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        
        /* Mission & Vision */
        .mission-vision {
            padding: 60px 0;
            background-color: white;
        }
        
        .mission-card, .vision-card {
            padding: 30px;
            border-radius: var(--border-radius);
            height: 100%;
            transition: all 0.3s ease;
        }
        
        .mission-card {
            background-color: var(--primary-color);
            color: white;
        }
        
        .vision-card {
            background-color: var(--light-color);
            color: var(--text-color);
            border: 1px solid #e2e8f0;
        }
        
        .mission-card:hover, .vision-card:hover {
            transform: translateY(-10px);
            box-shadow: var(--box-shadow);
        }
        
        .mission-card h3, .vision-card h3 {
            font-weight: 700;
            margin-bottom: 20px;
            font-size: 1.8rem;
        }
        
        .mission-card h3 {
            color: white;
        }
        
        .vision-card h3 {
            color: var(--dark-color);
        }
        
        .mission-card p, .vision-card p {
            line-height: 1.8;
            margin-bottom: 0;
        }
        
        .mission-icon, .vision-icon {
            font-size: 3rem;
            margin-bottom: 20px;
            animation: float 3s ease-in-out infinite;
        }
        
        .mission-icon {
            color: var(--secondary-color);
        }
        
        .vision-icon {
            color: var(--accent-color);
        }
        
        /* Team Section */
        .team-section {
            padding: 60px 0;
        }
        
        .team-card {
            background-color: white;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            overflow: hidden;
            margin-bottom: 30px;
            transition: all 0.3s ease;
        }
        
        .team-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
        }
        
        .team-image {
            height: 300px;
            position: relative;
            overflow: hidden;
        }
        
        .team-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.5s ease;
        }
        
        .team-card:hover .team-image img {
            transform: scale(1.05);
        }
        
        .team-content {
            padding: 20px;
            text-align: center;
        }
        
        .team-name {
            font-weight: 600;
            color: var(--dark-color);
            margin-bottom: 5px;
            font-size: 1.2rem;
        }
        
        .team-position {
            color: var(--accent-color);
            margin-bottom: 15px;
            font-size: 0.9rem;
        }
        
        .team-bio {
            margin-bottom: 15px;
            line-height: 1.6;
        }
        
        .team-social {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        
        .team-social a {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 35px;
            height: 35px;
            border-radius: 50%;
            background-color: var(--light-color);
            color: var(--dark-color);
            transition: all 0.3s ease;
        }
        
        .team-social a:hover {
            background-color: var(--accent-color);
            color: white;
            transform: translateY(-3px);
        }
        
        /* Stats Section */
        .stats-section {
            padding: 80px 0;
            background-color: var(--primary-color);
            color: white;
        }
        
        .stat-card {
            text-align: center;
            padding: 30px;
            border-radius: var(--border-radius);
            background-color: rgba(255, 255, 255, 0.1);
            margin-bottom: 30px;
            height: 100%;
            transition: all 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
            background-color: rgba(255, 255, 255, 0.15);
        }
        
        .stat-icon {
            font-size: 3rem;
            margin-bottom: 20px;
            color: var(--secondary-color);
            animation: pulse 2s infinite;
        }
        
        .stat-number {
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 10px;
            opacity: 0;
            transform: translateY(20px);
        }
        
        .stat-number.animate {
            animation: fadeInUp 1s forwards;
        }
        
        .stat-title {
            font-size: 1.1rem;
            opacity: 0.9;
        }
        
        /* Footer */
        footer {
            background-color: var(--primary-color);
            color: white;
            padding: 20px 0;
            margin-top: 0;
        }
        
        .copyright {
            text-align: center;
            color: rgba(255, 255, 255, 0.7);
        }
        
        /* Responsive Adjustments */
        @media (max-width: 992px) {
            .col-lg-3 {
                width: 50%;
            }
            
            .col-lg-6 {
                width: 100%;
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
            
            .mission-card, .vision-card {
                margin-bottom: 30px;
            }
        }
        
        @media (max-width: 768px) {
            .col-md-6 {
                width: 100%;
            }
            
            .navbar-toggler {
                display: block;
            }
            
            .navbar-collapse {
                display: none;
                position: absolute;
                top: 70px;
                left: 0;
                width: 100%;
                background-color: white;
                padding: 20px;
                box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
                flex-direction: column;
            }
            
            .navbar-collapse.show {
                display: flex;
            }
            
            .navbar-nav {
                flex-direction: column;
                width: 100%;
                margin-bottom: 20px;
            }
            
            .nav-item {
                margin: 10px 0;
            }
            
            .nav-icons {
                width: 100%;
                justify-content: center;
            }
            
            .hero-banner {
                padding: 60px 0;
            }
            
            .hero-title {
                font-size: 1.8rem;
            }
            
            .about-image {
                margin-bottom: 30px;
            }
            
            .stat-number {
                font-size: 2rem;
            }
        }
        
        @media (max-width: 576px) {
            .col-lg-3 {
                width: 100%;
            }
            
            .hero-banner {
                padding: 40px 0;
            }
            
            .hero-title {
                font-size: 1.5rem;
            }
            
            .section-title h2 {
                font-size: 1.5rem;
            }
            
            .about-card, .mission-card, .vision-card {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <!-- Hero Banner -->
    <section class="hero-banner">
        <div class="container text-center">
            <h1 class="hero-title">About TechGhar</h1>
            <p class="hero-subtitle">We're on a mission to provide premium tech products with exceptional service at competitive prices</p>
        </div>
    </section>

    <!-- About Section -->
    <section class="about-section">
        <div class="container">
            <div class="section-title">
                <h2>Our Story</h2>
                <p>Learn about our journey and what makes us different</p>
            </div>
            
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <div class="about-image">
                        <img src="/placeholder.svg?height=500&width=600&text=TechGhar+Store" alt="TechGhar Store">
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="about-card">
                        <h3>How We Started</h3>
                        <p>Founded in 2015, TechGhar began as a small tech repair shop in Pokhara, Nepal. Our founder, Bashanta Rokaha, had a vision to create a one-stop destination for all tech needs in the region. What started as a modest repair service quickly grew into a comprehensive tech retailer as customers began requesting new devices and accessories.</p>
                        <p>Over the years, we've expanded our product range to include the latest laptops, desktops, and accessories from leading brands worldwide. Our commitment to quality, authenticity, and customer satisfaction has helped us grow from a small local shop to one of Nepal's most trusted tech retailers.</p>
                        <p>Today, TechGhar serves thousands of customers across Nepal through our physical stores and e-commerce platform. We continue to uphold our founding principles of providing genuine products, expert advice, and exceptional after-sales service.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Mission & Vision -->
    <section class="mission-vision">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="mission-card">
                        <div class="mission-icon">
                            <i class="fas fa-bullseye"></i>
                        </div>
                        <h3>Our Mission</h3>
                        <p>To provide access to high-quality tech products and expert services that enhance people's lives and work. We strive to make premium technology accessible to everyone through competitive pricing, authentic products, and exceptional customer service.</p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="vision-card">
                        <div class="vision-icon">
                            <i class="fas fa-eye"></i>
                        </div>
                        <h3>Our Vision</h3>
                        <p>To become the leading tech retailer in Nepal, recognized for our product quality, customer service excellence, and technological expertise. We aim to foster digital literacy and technological advancement in our communities through education and access to cutting-edge technology.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Team Section -->
    <section class="team-section">
        <div class="container">
            <div class="section-title">
                <h2>Meet Our Team</h2>
                <p>The passionate people behind TechGhar</p>
            </div>
            
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="team-card">
                        <div class="team-image">
                            <img src="/placeholder.svg?height=300&width=300&text=" alt="">
                        </div>
                        <div class="team-content">
                            <h3 class="team-name">Jonsen Gaire</h3>
                            <div class="team-position">CEO</div>
                            <p class="team-bio">Tech enthusiast with over 15 years of experience in the industry. Jonsen helped TechGhar with a vision to transform tech retail in Nepal.</p>
                            <div class="team-social">
                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-facebook-f"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="team-card">
                        <div class="team-image">
                            <img src="/placeholder.svg?height=300&width=300&text" alt="">
                        </div>
                        <div class="team-content">
                            <h3 class="team-name">Manoj neupane</h3>
                            <div class="team-position">Operations Manager</div>
                            <p class="team-bio">With a background in supply chain management, Manoj ensures our operations run smoothly and customers receive their orders promptly.</p>
                            <div class="team-social">
                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="team-card">
                        <div class="team-image">
                            <img src="/placeholder.svg?height=300&width=300&text=" alt="">
                        </div>
                        <div class="team-content">
                            <h3 class="team-name">Shishir Rokaha</h3>
                            <div class="team-position">Technical Expert</div>
                            <p class="team-bio">A certified hardware specialist with expertise in laptop repairs and system optimization. Shishir leads our technical support team.</p>
                            <div class="team-social">
                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a href="#"><i class="fab fa-github"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="team-card">
                        <div class="team-image">
                            <img src="/placeholder.svg?height=300&width=300&text=" alt="">
                        </div>
                        <div class="team-content">
                            <h3 class="team-name">Saurav Shrestha</h3>
                            <div class="team-position">Customer Relations</div>
                            <p class="team-bio">With his friendly demeanor and problem-solving skills, Saurav ensures every customer has a positive experience with TechGhar.</p>
                            <div class="team-social">
                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a href="#"><i class="fab fa-facebook-f"></i></a>
                                <a href="#"><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Stats Section -->
    <section class="stats-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-users"></i>
                        </div>
                        <div class="stat-number" data-value="10000">0</div>
                        <div class="stat-title">Happy Customers</div>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-laptop"></i>
                        </div>
                        <div class="stat-number" data-value="5000">0</div>
                        <div class="stat-title">Products Sold</div>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-store"></i>
                        </div>
                        <div class="stat-number" data-value="3">0</div>
                        <div class="stat-title">Retail Locations</div>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-award"></i>
                        </div>
                        <div class="stat-number" data-value="8">0</div>
                        <div class="stat-title">Years of Excellence</div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</body>
</html>