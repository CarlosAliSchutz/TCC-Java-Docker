import { axiosInstance } from "../_base/axios-instance";
import { useRequest } from "../_base/use-request";

export const useAmizade = () => {
  const { handleRequest, data } = useRequest();

  function getAmizade() {
    handleRequest(axiosInstance.get("/amizade"));
  }
  return { getAmizade, pedidos: data };
};
