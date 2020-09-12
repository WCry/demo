package Minio;

import io.minio.BucketExistsArgs;
import io.minio.ComposeObjectArgs;
import io.minio.ComposeSource;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.RegionConflictException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestMinio {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
        try {
            // Create a minioClient with the MinIO Server name, Port, Access key and Secret key.
            MinioClient minioClient = new MinioClient("https://play.min.io",
                    "Q3AM3UQ867SPQQA43P2F",
                    "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
            // Check if the bucket already exists.
            boolean isExist =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // Make a new bucket called asiatrip to hold a zip file of photos.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
            }

            // Upload the zip file to the bucket with putObject
            minioClient.putObject("asiatrip",
                    "asiaphotos.zip", "C" +
                            ":\\Users" +
                            "\\zhangxuepei" +
                            "\\Desktop\\新建文件夹\\18M.jpg", null);
            // Upload the zip file to the bucket with putObject
            minioClient.putObject("asiatrip",
                    "asiaphotos2.zip", "C" +
                            ":\\Users" +
                            "\\zhangxuepei\\Desktop\\新建文件夹\\18M.jpg", null);
            ComposeSource composeSource=
                    ComposeSource.builder().object("asiaphotos.zip").bucket("asiatrip").build();
            ComposeSource composeSource2=
                    ComposeSource.builder().object("asiaphotos2.zip").bucket("asiatrip").build();
            List<ComposeSource> composeSourceList=new ArrayList<>();
            composeSourceList.add(composeSource);
            composeSourceList.add(composeSource2);
            minioClient.composeObject(ComposeObjectArgs.builder().sources(composeSourceList).
                    bucket("asiatrip").object(
                            "dsad").build());
            minioClient.removeObject("asiatrip","asiaphotos.zip");
            minioClient.removeObject("asiatrip"
                    ,"asiaphotos2.zip");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
