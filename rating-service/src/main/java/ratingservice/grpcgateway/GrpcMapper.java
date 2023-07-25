package ratingservice.grpcgateway;

import org.mapstruct.Mapper;
import ratingservice.dto.RateRequest;

@Mapper(componentModel = "spring")
public interface GrpcMapper {


    RateRequest toDto(ratingservice.grpcgateway.RateRequest request);

    RateResponse toGatewayDto(ratingservice.dto.RateResponse response);

    RatingAverage toGatewayDto(ratingservice.dto.RatingAverage average);
}
