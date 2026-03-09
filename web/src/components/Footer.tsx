const BMAC_URL = "https://buymeacoffee.com/anipmehta";
const GITHUB_URL = "https://github.com/anipmehta/AlgoForge";
const PROFILE_URL = "https://github.com/anipmehta";

export default function Footer() {
  return (
    <footer className="site-footer">
      <p>
        Built with ❤️ by{" "}
        <a href={PROFILE_URL} target="_blank" rel="noopener noreferrer">
          anipmehta
        </a>
        {" · "}
        <a href={BMAC_URL} target="_blank" rel="noopener noreferrer">
          ☕ Buy me a coffee
        </a>
        {" · "}
        <a href={GITHUB_URL} target="_blank" rel="noopener noreferrer">
          ⭐ Star on GitHub
        </a>
      </p>
    </footer>
  );
}
