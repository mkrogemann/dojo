require 'roman_numerals'

describe RomanNumerals do
  describe '#from_integer' do
    it "converts integers to roman numerals" do
      expect(RomanNumerals.from_integer(42)).to eq('XLII')
      expect(RomanNumerals.from_integer(1990)).to eq('MCMXC')
      expect(RomanNumerals.from_integer(2008)).to eq('MMVIII')
      expect(RomanNumerals.from_integer(99)).to eq('XCIX')
      expect(RomanNumerals.from_integer(47)).to eq('XLVII')
      expect(RomanNumerals.from_integer(3999)).to eq('MMMCMXCIX')
      expect(RomanNumerals.from_integer(0)).to eq('')
      expect(RomanNumerals.from_integer(4000)).to eq('MMMM')
      expect(RomanNumerals.from_integer(14324)).to eq('MMMMMMMMMMMMMMCCCXXIV')
    end
  end

  describe '#convert_digit' do
    it 'converts one digit with given order of magnitude into roman numerals' do
      expect(RomanNumerals.send(:convert_digit, 3, 1)).to eq('III')
      expect(RomanNumerals.send(:convert_digit, 4, 2)).to eq('XL')
      expect(RomanNumerals.send(:convert_digit, 9, 3)).to eq('CM')
      expect(RomanNumerals.send(:convert_digit, 8, 2)).to eq('LXXX')
    end
  end
end