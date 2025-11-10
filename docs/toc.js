// Populate the sidebar
//
// This is a script, and not included directly in the page, to control the total size of the book.
// The TOC contains an entry for each page, so if each page includes a copy of the TOC,
// the total size of the page becomes O(n**2).
class MDBookSidebarScrollbox extends HTMLElement {
    constructor() {
        super();
    }
    connectedCallback() {
        this.innerHTML = '<ol class="chapter"><li class="chapter-item expanded "><a href="about.html"><strong aria-hidden="true">1.</strong> About</a></li><li class="chapter-item expanded "><a href="solutions.html"><strong aria-hidden="true">2.</strong> Problem solutions</a></li><li><ol class="section"><li class="chapter-item expanded "><a href="explanations/1.html"><strong aria-hidden="true">2.1.</strong> Multiples of 3 or 5</a></li><li class="chapter-item expanded "><a href="explanations/2.html"><strong aria-hidden="true">2.2.</strong> Even Fibonacci Numbers</a></li><li class="chapter-item expanded "><a href="explanations/3.html"><strong aria-hidden="true">2.3.</strong> Largest Prime Factor</a></li><li class="chapter-item expanded "><a href="explanations/4.html"><strong aria-hidden="true">2.4.</strong> Largest Palindrome Product</a></li><li class="chapter-item expanded "><a href="explanations/5.html"><strong aria-hidden="true">2.5.</strong> Smallest Multiple</a></li><li class="chapter-item expanded "><a href="explanations/6.html"><strong aria-hidden="true">2.6.</strong> Sum Square Difference</a></li><li class="chapter-item expanded "><a href="explanations/7.html"><strong aria-hidden="true">2.7.</strong> 10 001st Prime</a></li><li class="chapter-item expanded "><a href="explanations/8.html"><strong aria-hidden="true">2.8.</strong> Largest Product in a Series</a></li><li class="chapter-item expanded "><a href="explanations/9.html"><strong aria-hidden="true">2.9.</strong> Special Pythagorean Triplet</a></li><li class="chapter-item expanded "><a href="explanations/10.html"><strong aria-hidden="true">2.10.</strong> Summation of Primes</a></li></ol></li></ol>';
        // Set the current, active page, and reveal it if it's hidden
        let current_page = document.location.href.toString().split("#")[0].split("?")[0];
        if (current_page.endsWith("/")) {
            current_page += "index.html";
        }
        var links = Array.prototype.slice.call(this.querySelectorAll("a"));
        var l = links.length;
        for (var i = 0; i < l; ++i) {
            var link = links[i];
            var href = link.getAttribute("href");
            if (href && !href.startsWith("#") && !/^(?:[a-z+]+:)?\/\//.test(href)) {
                link.href = path_to_root + href;
            }
            // The "index" page is supposed to alias the first chapter in the book.
            if (link.href === current_page || (i === 0 && path_to_root === "" && current_page.endsWith("/index.html"))) {
                link.classList.add("active");
                var parent = link.parentElement;
                if (parent && parent.classList.contains("chapter-item")) {
                    parent.classList.add("expanded");
                }
                while (parent) {
                    if (parent.tagName === "LI" && parent.previousElementSibling) {
                        if (parent.previousElementSibling.classList.contains("chapter-item")) {
                            parent.previousElementSibling.classList.add("expanded");
                        }
                    }
                    parent = parent.parentElement;
                }
            }
        }
        // Track and set sidebar scroll position
        this.addEventListener('click', function(e) {
            if (e.target.tagName === 'A') {
                sessionStorage.setItem('sidebar-scroll', this.scrollTop);
            }
        }, { passive: true });
        var sidebarScrollTop = sessionStorage.getItem('sidebar-scroll');
        sessionStorage.removeItem('sidebar-scroll');
        if (sidebarScrollTop) {
            // preserve sidebar scroll position when navigating via links within sidebar
            this.scrollTop = sidebarScrollTop;
        } else {
            // scroll sidebar to current active section when navigating via "next/previous chapter" buttons
            var activeSection = document.querySelector('#sidebar .active');
            if (activeSection) {
                activeSection.scrollIntoView({ block: 'center' });
            }
        }
        // Toggle buttons
        var sidebarAnchorToggles = document.querySelectorAll('#sidebar a.toggle');
        function toggleSection(ev) {
            ev.currentTarget.parentElement.classList.toggle('expanded');
        }
        Array.from(sidebarAnchorToggles).forEach(function (el) {
            el.addEventListener('click', toggleSection);
        });
    }
}
window.customElements.define("mdbook-sidebar-scrollbox", MDBookSidebarScrollbox);
