import { createBrowserRouter } from "react-router-dom";
import {
  AmizadesScreen,
  CadastroScreen,
  HomeScreen,
  LoginScreen,
  PerfilScreen,
} from "../ui/screens";
import { PrivateRoute } from "./private-route.component";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <LoginScreen />,
  },
  {
    path: "/cadastro",
    element: <CadastroScreen />,
  },
  {
    path: "/perfil",
    element: <PrivateRoute Screen={PerfilScreen} />,
  },
  {
    path: "/perfil/amizades",
    element: <PrivateRoute Screen={AmizadesScreen} />,
  },

  {
    path: "/home",
    element: <PrivateRoute Screen={HomeScreen} />,
  },
]);
