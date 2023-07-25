package ratingservice.grpcgateway;

import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ratingservice.service.RatingService;

@GrpcService
@AllArgsConstructor
public class RatingServiceGateway extends RatingServiceGrpc.RatingServiceImplBase {

    private RatingService ratingService;

    private GrpcMapper grpcMapper;

    @Override
    public void rate(RateRequest request, StreamObserver<RateResponse> responseObserver) {
        var response =  ratingService.createRating(grpcMapper.toDto(request));
        responseObserver.onNext(grpcMapper.toGatewayDto(response));
        responseObserver.onCompleted();
    }

    @Override
    public void findAverageOfCourse(Int64Value request, StreamObserver<RatingAverage> responseObserver) {
        var average = ratingService.findAverageByCourseId(request.getValue());
        responseObserver.onNext(grpcMapper.toGatewayDto(average));
        responseObserver.onCompleted();
    }
}
