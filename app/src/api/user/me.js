import { axiosInstance } from "../_base/axios-instance";
import { useRequest } from "../_base/use-request";

export const useProfile = () => {
  const { handleRequest, data } = useRequest();

  function getProfile() {
    handleRequest(
      axiosInstance.get('/usuarios/me'));
  }
  return { getProfile, perfil: data };
};
