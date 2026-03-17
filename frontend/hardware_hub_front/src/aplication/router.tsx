import { createBrowserRouter } from 'react-router-dom';
import LoginPage from '@/pages/LoginPage';
// import UsersPage from '@/pages/UsersPage';
export default createBrowserRouter([
  {
    path: '/login',
    element: <LoginPage />,
  },
  // anadir rutas
]);
