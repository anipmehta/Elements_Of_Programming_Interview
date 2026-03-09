import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  base: '/Elements_Of_Programming_Interview/',
  plugins: [react()],
})
