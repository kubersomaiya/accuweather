package com.example.demo;

public class ResponseServiceImpl implements ResponseServiceIntf
{
        
    @Override
    public SuccessDTO getSuccessResponse(int code,String message,Object data)
    {
        SuccessDTO successDTO=new SuccessDTO();
        successDTO.setCode(code);
        successDTO.setMessage(message);
        successDTO.setData(data);
        return successDTO;
    }
    
    @Override
    public ErrorDTO getErrorResponse(int code,String message)
    {
        ErrorDTO  errorDTO=new ErrorDTO();
        errorDTO.setCode(code);
        errorDTO.setMessage(message);
        
        return errorDTO;
        
    }


}
