// Smooth scrolling for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
            behavior: 'smooth'
        });
    });
});

// Scroll indicator
window.addEventListener('scroll', () => {
    const scrollIndicator = document.getElementById('scrollIndicator');
    const totalHeight = document.documentElement.scrollHeight - window.innerHeight;
    const scrollProgress = (window.scrollY / totalHeight) * 100;
    scrollIndicator.style.width = scrollProgress + '%';
});

// Dynamic particles for hero section
const particlesContainer = document.getElementById('particles');
const numParticles = 30;

for (let i = 0; i < numParticles; i++) {
    const particle = document.createElement('div');
    particle.classList.add('particle');
    particle.style.left = `${Math.random() * 100}%`;
    particle.style.animationDuration = `${Math.random() * 5 + 5}s`; // 5 to 10 seconds
    particle.style.animationDelay = `${Math.random() * 6}s`; // 0 to 6 seconds delay
    particlesContainer.appendChild(particle);
}

// Form submission handling (Formspree or other endpoints)
const form = document.querySelector('form');
const statusMessage = document.getElementById('statusMessage');

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    statusMessage.style.display = 'none'; // Hide previous message

    const formData = new FormData(form);
    try {
        const response = await fetch(form.action, {
            method: form.method,
            body: formData,
            headers: {
                'Accept': 'application/json'
            }
        });

        if (response.ok) {
            statusMessage.textContent = 'Message sent successfully! I\'ll get back to you soon.';
            statusMessage.classList.remove('status-error');
            statusMessage.classList.add('status-success');
            form.reset();
        } else {
            const data = await response.json().catch(() => ({})); // fallback if no JSON
            if (response.status === 404) {
                statusMessage.textContent = 'Sorry, there was an error sending your message. Please try again or contact me directly at kondasandeep56@gmail.com.';
            } else if (Object.hasOwnProperty.call(data, 'errors')) {
                statusMessage.textContent = data["errors"].map(error => error["message"]).join(", ");
            } else {
                statusMessage.textContent = 'Oops! There was a problem sending your message.';
            }
            statusMessage.classList.remove('status-success');
            statusMessage.classList.add('status-error');
        }
    } catch (error) {
        statusMessage.textContent = 'Oops! There was a network error. Please try again.';
        statusMessage.classList.remove('status-success');
        statusMessage.classList.add('status-error');
    } finally {
        statusMessage.style.display = 'block';
    }
});

// --- Header Hide/Show on Scroll Logic ---
const navbar = document.querySelector('.navbar');
let lastScrollY = window.scrollY;

window.addEventListener('scroll', () => {
    if (window.scrollY > lastScrollY) {
        // Scrolling Down
        navbar.classList.add('navbar-hidden');
    } else {
        // Scrolling Up
        navbar.classList.remove('navbar-hidden');
    }
    lastScrollY = window.scrollY;
});
// --- End Header Hide/Show on Scroll Logic ---
