package net.javaguides.lms.mapper;

import net.javaguides.lms.dto.BorrowingRecordDto;
import net.javaguides.lms.dto.PatronDto;
import net.javaguides.lms.entity.BorrowingRecord;
import net.javaguides.lms.entity.Patron;

public class BorrowingRecordMapper {
    public static BorrowingRecordDto mapToPatronDto(BorrowingRecord borrowingRecord){
        return new BorrowingRecordDto(
                borrowingRecord.getId(),
                borrowingRecord.getBook(),
                borrowingRecord.getPatron(),
                borrowingRecord.getBorrowingDate(),
                borrowingRecord.getReturnDate()
        );
    }

    public static BorrowingRecord mapToPatronDto(BorrowingRecordDto borrowingRecordDto){
        return new BorrowingRecord(
                borrowingRecordDto.getId(),
                borrowingRecordDto.getBook(),
                borrowingRecordDto.getPatron(),
                borrowingRecordDto.getBorrowingDate(),
                borrowingRecordDto.getReturnDate()
        );
    }
}
